package ITProject.example.WebSelling.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AuthenticationResponse {
    boolean authenticated;
    String token;
}
