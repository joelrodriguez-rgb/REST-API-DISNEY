package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.User;
import app.disney.ports.input.rs.request.CreateUserRequest;
import app.disney.ports.input.rs.response.UserResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T23:21:37-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
public class AuthenticationControllerMapperImpl implements AuthenticationControllerMapper {

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

    @Override
    public User createUserRequestToUser(CreateUserRequest user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );

        return user1;
    }
}
