package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.UserRequests.UserRequest;
import ITProject.example.WebSelling.dto.response.UserResponse;
import ITProject.example.WebSelling.service.intefaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    IUserService userService;


    @PostMapping("/")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(
                userService.save(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(userRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

}
