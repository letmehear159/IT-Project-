package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.RoleRequest;
import ITProject.example.WebSelling.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

}
