package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.UserRequest;
import ITProject.example.WebSelling.dto.response.UserResponse;
import ITProject.example.WebSelling.entity.User;

import java.util.List;

public interface IUserService {
    public UserResponse save(UserRequest userRequest);


    public UserResponse update(UserRequest userRequest, Long id);

    public UserResponse getUserById(Long userId);

    public void delete(Long userId);

    public List<UserResponse> getAllUsers();

    public UserResponse findUserByUsername(String username);



}
