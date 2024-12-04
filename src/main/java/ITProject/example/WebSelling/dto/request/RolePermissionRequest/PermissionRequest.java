package ITProject.example.WebSelling.dto.request.RolePermissionRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PermissionRequest {
    String permissionId;

    String description;
}
