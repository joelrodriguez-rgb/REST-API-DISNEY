package app.disney.service;

import java.util.List;

import app.disney.DTO.AppUserDto;
import app.disney.entitys.AppRole;
import app.disney.entitys.AppUser;

public interface IUserService {
	
	AppUser saveUser(AppUserDto user);
	
	void addRole(String userName, AppRole sole);
		
	List<AppUser> getAllUser();


}
