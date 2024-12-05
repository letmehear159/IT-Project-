//package ITProject.example.WebSelling.config;
//
//import ITProject.example.WebSelling.dto.response.IntrospectResponse;
//import ITProject.example.WebSelling.service.impl.AuthenticationService;
//import com.nimbusds.jose.JOSEException;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.text.ParseException;
//import java.util.Objects;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class CustomJwtDecoder implements JwtDecoder {
//
//    @Autowired
//    JwtConfig jwtConfig;
//
//    @Autowired
//    AuthenticationService authenticationService;
//
//    NimbusJwtDecoder nimbusJwtDecoder = null;
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        IntrospectResponse introspectResponse;
//        try {
//            introspectResponse = authenticationService.introspect(token);
//        } catch (ParseException | JOSEException e) {
//            throw new JwtException(e.getMessage());
//        }
//        if (!introspectResponse.isValidToken()) {
//            throw new JwtException("Invalid token");
//        }
//        if (Objects.isNull(nimbusJwtDecoder)) {
//            SecretKeySpec keySpec = new SecretKeySpec(jwtConfig.getSecretKey().getBytes(), "HS512");
//            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(keySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//        }
//        return nimbusJwtDecoder.decode(token);
//    }
//}
