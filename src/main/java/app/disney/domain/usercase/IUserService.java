package app.disney.domain.usercase;

import app.disney.domain.model.AppUser;

public interface IUserService {

    void updateEntityIfExists(Long id, AppUser user);

    void deleteUserById(Long id);

    AppUser createUser(AppUser user);

}
