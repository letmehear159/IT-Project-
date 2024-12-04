package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.RolePermissionRequest.RoleRequest;
import ITProject.example.WebSelling.entity.Role;
import ITProject.example.WebSelling.response.APIResponse;
import ITProject.example.WebSelling.service.intefaces.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleController {
    IRoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.createRole(roleRequest));

    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAll() {

        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/{roleName}")
    public APIResponse<Void> delete(@PathVariable("roleName") String roleName) {

        roleService.delete(roleName);

        return APIResponse.<Void>builder().result(null).build();
    }
}
