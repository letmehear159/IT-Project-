package ITProject.example.WebSelling.dto.response;

import ITProject.example.WebSelling.entity.Order;
import ITProject.example.WebSelling.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserResponse extends BaseResponse {

    String username;

    String fullName;

    String email;

    String phone;

    @JsonProperty("Date Of Birth")
    LocalDate dob;

    List<Role> roles;

    @JsonProperty("created_at")
    LocalDateTime dateCreated;

    @JsonProperty("updated_at")
    LocalDateTime dateModified;

}
