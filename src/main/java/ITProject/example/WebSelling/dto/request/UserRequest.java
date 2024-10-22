package ITProject.example.WebSelling.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserRequest {

     String username;


    @NotBlank(message = "Password cannot be blank")
    String password;

//    @JsonProperty("retype_password")
//    String retypePassword;

    String fullName;

    String email;

    String phone;

    LocalDate dob;

    Integer state = 1;

}
