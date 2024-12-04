package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.PermissionRequest;
import ITProject.example.WebSelling.entity.Permission;
import ITProject.example.WebSelling.response.APIResponse;
import ITProject.example.WebSelling.service.intefaces.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PermissionController {

    IPermissionService permissionService;

    @PostMapping("/")
    public ResponseEntity<Permission> create(@RequestBody PermissionRequest permissionRequest) {
        var permission = permissionService.createPermission(permissionRequest);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/")
    public ResponseEntity<List<Permission>> getAll() {
        return ResponseEntity.ok(permissionService.getAll());
    }

    @DeleteMapping("/{permissionName}")
    public APIResponse<Void> delete(@PathVariable("permissionName") String permissionName) {
        permissionService.delete(permissionName);
        return APIResponse.<Void>builder().result(null).build();
    }
}
