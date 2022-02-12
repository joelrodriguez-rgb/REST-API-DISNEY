package app.disney.security;

import java.util.List;

public interface IUserService {
	
	AppUser saveUser(AppUser user);
	
	void addRole(String userName, Role sole);
	
	AppUser getUser(String userName);
	
	List<AppUser> getAllUser();

}
