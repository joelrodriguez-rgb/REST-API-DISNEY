package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.AppUser;
import app.disney.ports.input.rs.request.CreateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticationControllerMapper extends CommonMapper {


    UserResponse userToUserResponse(AppUser user);

    AppUser createUserRequestToUser(CreateUserRequest user);
}
