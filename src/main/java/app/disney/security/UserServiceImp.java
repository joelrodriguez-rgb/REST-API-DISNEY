package app.disney.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.disney.exceptions.ExistingNameException;

@Service
public class UserServiceImp implements IUserService{
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public AppUser saveUser(AppUserDto userDto) {
		
		if (userRepo.findByUserName(userDto.getUserName()) != null) {
			throw new ExistingNameException("USER NAME : "+ userDto.getUserName());
		}
		
		AppUser user = mapper.map(userDto, AppUser.class);
		user.setRoles(Arrays.asList(Role.USER));
		
		return userRepo.save(user);
	}

	@Override
	public void addRole(String userName, Role role) {
		AppUser user = userRepo.findByUserName(userName);
		user.getRoles().add(role);
	}

	@Override
	public List<AppUser> getAllUser() {
		 List<AppUser> listAllUser =  userRepo.findAll();
		
		return listAllUser;
	}
	
	
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findByUserName(username);	
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		
		
		
		return null;
	}

	
		@Override
	public AppUser getUserByUserName(String userName) {
		AppUser user = userRepo.findByUserName(userName);
		
		
		
		return user;
	}
}
