package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.User;
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
    public User updateUserRequestToUser(UpdateUserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( userRequest.getFirstName() );
        user.setLastName( userRequest.getLastName() );
        user.setPassword( userRequest.getPassword() );
        user.setPhoto( userRequest.getPhoto() );

        return user;
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPhoto( user.getPhoto() );

        return userResponse;
    }
}
