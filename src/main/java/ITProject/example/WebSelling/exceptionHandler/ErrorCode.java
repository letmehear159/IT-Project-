package ITProject.example.WebSelling.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_EXIST(1001, "User already exists", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED(9999, "Uncategorized", HttpStatus.SERVICE_UNAVAILABLE),
    USERNAME_INVALID(1002, "Username's length must be greater or equal 5 characters", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1003, "Invalid email address", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password's length must be greater or equal 8 characters", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005, "Can not find appropriate key", HttpStatus.NOT_FOUND),
    USER_NON_EXISTENT(1006, "User does not exist", HttpStatus.NOT_FOUND),
    USERNAME_PASSWORD_INVALID(1007, "User or password is incorrect", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(1008, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "Unauthorized", HttpStatus.FORBIDDEN),
    INVALID_USERID(1010, "Character is not allowed is User ID", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1011, "Invalid date of birth, age must be at least {min}", HttpStatus.BAD_REQUEST),
    VALUE_NOT_FOUND(1012, "Value does not exist", HttpStatus.NOT_FOUND),
    INVALID_NAME(1013, "Name is invalid", HttpStatus.BAD_REQUEST),
    INVALID_ID(1014, "Id is invalid", HttpStatus.BAD_REQUEST),
    EXCEED_MAXIMUM_ALLOW_NUMBER(1015,"File images are more than 5 to saved in database", HttpStatus.BAD_REQUEST),
    FILE_IMAGE_TOO_LARGE(1016, "File image is too large", HttpStatus.PAYLOAD_TOO_LARGE),
    UPLOAD_IMAGE_MUST_BE_IMAGE_TYPE(1017, "File type must be image type", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    INVALID_SHOPPINGCART_ID(1018,"Shopping cart Id is invalid", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_ID(1019, "Product Id is invalid", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
