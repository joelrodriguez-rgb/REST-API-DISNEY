package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.User;
import app.disney.ports.input.rs.request.UpdateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserControllerMapper {

    User updateUserRequestToUser(UpdateUserRequest userRequest);

    UserResponse userToUserResponse(User user);
}
