package ITProject.example.WebSelling.mapper;

import ITProject.example.WebSelling.dto.request.UserRequests.UserRequest;
import ITProject.example.WebSelling.dto.response.UserResponse;
import ITProject.example.WebSelling.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

    void updateUserFromDto(UserRequest dto, @MappingTarget User user);


}
