package ITProject.example.WebSelling.service.impl;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.PermissionRequest;
import ITProject.example.WebSelling.entity.Permission;
import ITProject.example.WebSelling.mapper.PermissionMapper;
import ITProject.example.WebSelling.repository.PermissionRepository;
import ITProject.example.WebSelling.service.intefaces.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public Permission createPermission(PermissionRequest permissionRequest) {

        Permission permission = permissionMapper.toPermission(permissionRequest);
        return permissionRepository.save(permission);

    }

    public List<Permission> getAll() {
        return permissionRepository.findAll();

    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
