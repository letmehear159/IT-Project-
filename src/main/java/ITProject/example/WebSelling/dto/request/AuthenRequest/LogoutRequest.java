package ITProject.example.WebSelling.dto.request.AuthenRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LogoutRequest {
    String token;
}
