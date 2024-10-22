package ITProject.example.WebSelling.service.intefaces;

import ITProject.example.WebSelling.dto.request.UserRequest;
import ITProject.example.WebSelling.dto.response.UserResponse;
import ITProject.example.WebSelling.entity.User;

public interface IUserService {
    public UserResponse save(UserRequest userRequest);


    public User update(UserRequest userRequest);

    public UserResponse getUserById(String id);

}
