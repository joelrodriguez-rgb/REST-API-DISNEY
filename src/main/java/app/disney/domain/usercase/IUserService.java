package app.disney.domain.usercase;

import java.util.List;

import app.disney.ports.input.rs.request.AppUserDto;
import app.disney.domain.model.AppRole;
import app.disney.domain.model.AppUser;

public interface IUserService {
	
	AppUser saveUser(AppUserDto user);
	
	void addRole(String userName, AppRole sole);
		
	List<AppUser> getAllUser();
	
	AppUser getUser(String userName);


}
