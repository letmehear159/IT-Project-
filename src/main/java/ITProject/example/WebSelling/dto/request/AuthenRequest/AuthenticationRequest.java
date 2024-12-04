package ITProject.example.WebSelling.dto.request.AuthenRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AuthenticationRequest {
    String username;
    String password;
}
