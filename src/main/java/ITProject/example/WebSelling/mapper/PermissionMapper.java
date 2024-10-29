package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.PermissionRequest;
import ITProject.example.WebSelling.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
}
