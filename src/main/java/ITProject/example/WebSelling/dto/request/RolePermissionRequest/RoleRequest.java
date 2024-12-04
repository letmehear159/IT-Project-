package ITProject.example.WebSelling.dto.request.RolePermissionRequest;//package practice.example.DatabaseConnection.dto.request;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleRequest {
    String roleId;
    String description;
    Set<String> permissions;
}
