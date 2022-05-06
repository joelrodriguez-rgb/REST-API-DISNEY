package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.AppUser;
import app.disney.ports.input.rs.request.CreateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class AuthenticationControllerMapperImpl implements AuthenticationControllerMapper {

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

    @Override
    public AppUser createUserRequestToUser(CreateUserRequest user) {
        if ( user == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setPassword( user.getPassword() );
        appUser.setEmail( user.getEmail() );

        return appUser;
    }
}
