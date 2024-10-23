package ITProject.example.WebSelling.exceptionHandler;

import ITProject.example.WebSelling.response.APIResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

import ITProject.example.WebSelling.response.APIResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<APIResponse> runtimeExceptionHandler(Exception ex) {
        APIResponse response = new APIResponse();
        log.error(ex.getMessage(), ex);
        response.setCode(ErrorCode.UNCATEGORIZED.getCode());
        response.setMessage(ErrorCode.UNCATEGORIZED.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<APIResponse> appExceptionHandler(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        APIResponse apiResponse = new APIResponse();
        log.error(ex.getMessage(), ex);
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String enumKey = ex.getFieldError().getDefaultMessage();
        log.error(ex.getMessage(), ex);
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attribute = null;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constraintViolations =
                    ex.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attribute = constraintViolations.getConstraintDescriptor().getAttributes();
            log.info(attribute.toString());

        } catch (IllegalArgumentException e) {

        }
        APIResponse response = new APIResponse();
        response.setCode(errorCode.getCode());

        response.setMessage(
                Objects.nonNull(attribute) ? mapAttribute(errorCode.getMessage(), attribute) : errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(value = AuthorizationDeniedException.class)
//    public ResponseEntity<APIResponse> authorizationDeniedExceptionHandler(AuthorizationDeniedException ex) {
//        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
//        APIResponse response = APIResponse.builder()
//                .code(errorCode.getCode())
//                .message(errorCode.getMessage())
//                .build();
//        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
//    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<APIResponse> numberFormatExceptionHandler(NumberFormatException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_USERID;
        APIResponse response = APIResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
