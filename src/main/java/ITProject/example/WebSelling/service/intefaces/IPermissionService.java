package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.PermissionRequest;
import ITProject.example.WebSelling.entity.Permission;

import java.util.List;

public interface IPermissionService {
    Permission createPermission(PermissionRequest permissionRequest);

    List<Permission> getAll();

    void delete(String permission);
}
