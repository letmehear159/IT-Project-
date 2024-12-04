package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.RoleRequest;
import ITProject.example.WebSelling.entity.Role;

import java.util.List;

public interface IRoleService {
    Role createRole(RoleRequest roleRequest);

    List<Role> getAll();

    void delete(String roleId);
}
