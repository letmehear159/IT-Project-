package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.config.JwtConfig;
import ITProject.example.WebSelling.dto.request.AuthenRequest.AuthenticationRequest;
import ITProject.example.WebSelling.dto.request.AuthenRequest.LogoutRequest;
import ITProject.example.WebSelling.dto.request.AuthenRequest.RefreshTokenRequest;
import ITProject.example.WebSelling.dto.response.AuthenticationResponse;
import ITProject.example.WebSelling.dto.response.IntrospectResponse;
import ITProject.example.WebSelling.entity.InvalidatedToken;
import ITProject.example.WebSelling.entity.User;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.repository.UserRepository;
import ITProject.example.WebSelling.response.APIResponse;
import ITProject.example.WebSelling.service.intefaces.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;
    JwtConfig jwtConfig;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long duration;

    public Boolean authenticate(AuthenticationRequest AuthenticationRequest, String password) {
        var user = userRepository
                .findByUsername(AuthenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NON_EXISTENT));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    String buildToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("DatabaseConnection")
                .issueTime(new Date())
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(Instant.now()
                        .plus(jwtConfig.getValidDuration(), ChronoUnit.SECONDS)
                        .toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(jwtConfig.getSecretKey().getBytes()));
        return jwsObject.serialize();
    }

    public String generateToken(AuthenticationRequest authenticationRequest) throws JOSEException {
        User user = userRepository
                .findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NON_EXISTENT));

        return buildToken(user);
    }

    public IntrospectResponse introspect(String token) throws JOSEException, ParseException {
        boolean isValidToken = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValidToken = false;
        }

        return IntrospectResponse.builder().validToken(isValidToken).build();
    }

    public String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRoleId());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getPermissionId());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    public SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier jwsVerifier = new MACVerifier(jwtConfig.getSecretKey().getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean validKey = signedJWT.verify(jwsVerifier);
        boolean timeExpiration = (isRefresh)
                ? new Date(signedJWT
                                .getJWTClaimsSet()
                                .getIssueTime()
                                .toInstant()
                                .plus(jwtConfig.getRefreshableDuration(), ChronoUnit.SECONDS)
                                .toEpochMilli())
                        .after(new Date())
                : signedJWT
                        .getJWTClaimsSet()
                        .getExpirationTime()
                        .after(new Date()); // Lấy thời gian hết hạn (ĐÚng ngày giờ tháng năm để so với thời gian lúc
        // verify
        // nếu trả về true nghĩa là sau ==> hợp lệ
        if (!(validKey && timeExpiration)
                || invalidatedTokenRepository.existsById(
                        signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    public APIResponse<Void> logOut(LogoutRequest logoutRequest) throws ParseException, JOSEException {

        try {
            var signedToken = verifyToken(logoutRequest.getToken(), true);

            var expirationRefreshDate = new Date(signedToken
                    .getJWTClaimsSet()
                    .getIssueTime()
                    .toInstant()
                    .plus(jwtConfig.getRefreshableDuration(), ChronoUnit.SECONDS)
                    .toEpochMilli());
            var jwtID = signedToken.getJWTClaimsSet().getJWTID();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .tokenId(jwtID)
                    .expiryDate(expirationRefreshDate)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);

            return APIResponse.<Void>builder().message("Logout Successfully").build();
        } catch (AppException e) {
            return APIResponse.<Void>builder()
                    .message("Token is not valid to logout")
                    .build();
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var invalidatedToken =
                InvalidatedToken.builder().tokenId(jit).expiryDate(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NON_EXISTENT));

        var token = buildToken(user);

        return AuthenticationResponse.builder().authenticated(true).token(token).build();
    }
}
