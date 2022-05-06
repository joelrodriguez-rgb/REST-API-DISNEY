package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.AppUser;
import app.disney.ports.input.rs.request.UpdateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class UserControllerMapperImpl implements UserControllerMapper {

    @Override
    public AppUser updateUserRequestToUser(UpdateUserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setPassword( userRequest.getPassword() );

        return appUser;
    }

    @Override
    public UserResponse userToUserResponse(AppUser user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }
}
