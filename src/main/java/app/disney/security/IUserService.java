package app.disney.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
	
	AppUser saveUser(AppUserDto user);
	
	void addRole(String userName, Role sole);
	
	AppUser getUserByUserName(String userName);
	
	List<AppUser> getAllUser();

}
