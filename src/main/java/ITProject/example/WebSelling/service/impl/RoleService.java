package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.RoleRequest;
import ITProject.example.WebSelling.entity.Permission;
import ITProject.example.WebSelling.entity.Role;
import ITProject.example.WebSelling.mapper.PermissionMapper;
import ITProject.example.WebSelling.mapper.RoleMapper;
import ITProject.example.WebSelling.repository.PermissionRepository;
import ITProject.example.WebSelling.repository.RoleRepository;
import ITProject.example.WebSelling.service.intefaces.IRoleService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {

    RoleRepository roleRepository;

    RoleMapper roleMapper;

    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    public Role createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);

        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        Set<Permission> permissionSet = new HashSet<>(permissions);

        role.setPermissions(permissionSet);

        return roleRepository.save(role);


    }

    public List<Role> getAll() {

        return roleRepository.findAll();

    }

    public void delete(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
