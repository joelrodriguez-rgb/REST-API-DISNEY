package app.disney.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.exceptions.ExistingNameException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements IUserService{
	
	@Autowired
	private IUserRepository userRepo;

	@Override
	public AppUser saveUser(AppUser user) {
		
		if (userRepo.findByUserName(user.getUserName()) != null) {
			throw new ExistingNameException("USER NAME : "+ user.getUserName());
		}
		
		return userRepo.save(user);
	}

	@Override
	public void addRole(String userName, Role role) {
		AppUser user = userRepo.findByUserName(userName);
		user.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String userName) {
		AppUser user = userRepo.findByUserName(userName);
		return user;
	}

	@Override
	public List<AppUser> getAllUser() {
		 List<AppUser> listAllUser =  userRepo.findAll();
		
		return listAllUser;
	}

	
	
}
