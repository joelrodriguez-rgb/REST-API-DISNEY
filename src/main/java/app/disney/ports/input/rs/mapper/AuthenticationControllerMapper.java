package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.User;
import app.disney.ports.input.rs.request.CreateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticationControllerMapper extends CommonMapper {


    UserResponse userToUserResponse(User user);

    User createUserRequestToUser(CreateUserRequest user);
}
