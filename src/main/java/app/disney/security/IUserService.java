package app.disney.security;

import java.util.List;

public interface IUserService {
	
	AppUser saveUser(AppUserDto user);
	
	void addRole(String userName, AppRole sole);
		
	List<AppUser> getAllUser();


}
