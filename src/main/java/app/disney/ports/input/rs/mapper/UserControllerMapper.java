package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.AppUser;
import app.disney.ports.input.rs.request.UpdateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserControllerMapper {

    AppUser updateUserRequestToUser(UpdateUserRequest userRequest);

    UserResponse userToUserResponse(AppUser user);
}
