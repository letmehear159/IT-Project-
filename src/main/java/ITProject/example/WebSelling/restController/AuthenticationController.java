package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.AuthenRequest.AuthenticationRequest;
import ITProject.example.WebSelling.dto.request.AuthenRequest.IntrospectRequest;
import ITProject.example.WebSelling.dto.request.AuthenRequest.LogoutRequest;
import ITProject.example.WebSelling.dto.request.AuthenRequest.RefreshTokenRequest;
import ITProject.example.WebSelling.dto.response.AuthenticationResponse;
import ITProject.example.WebSelling.dto.response.IntrospectResponse;
import ITProject.example.WebSelling.exceptionHandler.AppException;
import ITProject.example.WebSelling.exceptionHandler.ErrorCode;
import ITProject.example.WebSelling.response.APIResponse;
import ITProject.example.WebSelling.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

//    @RequestMapping(method = RequestMethod.OPTIONS, value = "/login")
//    public ResponseEntity<?> handleOptionsRequest() {
//        return ResponseEntity.ok().build();
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
            throws JOSEException {
        boolean result = authenticationService.authenticate(authenticationRequest, authenticationRequest.getPassword());
        if (!result) {
            throw new AppException(ErrorCode.USERNAME_PASSWORD_INVALID);
        }
        // Nếu user password đúng thì tạo token
        String token = authenticationService.generateToken(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(true, token));
    }

    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest)
            throws JOSEException, ParseException {

        IntrospectResponse introspectResponse = authenticationService.introspect(introspectRequest.getToken());
        return ResponseEntity.ok(introspectResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody LogoutRequest logoutRequest)
            throws ParseException, JOSEException {
        var response = authenticationService.logOut(logoutRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<APIResponse<AuthenticationResponse>> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        return ResponseEntity.ok(APIResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(refreshTokenRequest))
                .build());
    }
}
