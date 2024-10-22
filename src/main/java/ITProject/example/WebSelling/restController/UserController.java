package ITProject.example.WebSelling.restController;

import ITProject.example.WebSelling.dto.request.UserRequest;
import ITProject.example.WebSelling.dto.response.UserResponse;
import ITProject.example.WebSelling.entity.User;
import ITProject.example.WebSelling.service.intefaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    IUserService IUserService;

    @PostMapping("/")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(IUserService.save(userRequest));
    }
}
