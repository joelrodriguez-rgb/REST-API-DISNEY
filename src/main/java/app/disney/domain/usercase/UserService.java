package app.disney.domain.usercase;

import app.disney.domain.model.User;

public interface UserService {

    void updateEntityIfExists(Long id, User user);

    void deleteUserById(Long id);

    User createUser(User user);

}
