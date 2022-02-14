package app.disney.service.implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.disney.DTO.AppUserDto;
import app.disney.entitys.AppRole;
import app.disney.entitys.AppUser;
import app.disney.exceptions.ExistingNameException;
import app.disney.repository.IUserRepository;
import app.disney.service.IRoleService;
import app.disney.service.IUserService;

@Service
public class UserServiceImp implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder bcrypt;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findByUserName(username);

		if (user == null) 
			throw new UsernameNotFoundException("Nombre de usuario o contraseña incorrecta");
		

		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles()
		    .forEach
		    (roles -> authorities.add(new SimpleGrantedAuthority(roles.getAuthority())));

		UserDetails userDetail = new User(user.getUserName(), user.getPassword(), authorities);

		return userDetail;
	}

	@Override
	public AppUser saveUser(AppUserDto userDto) {

		if (userRepo.findByUserName(userDto.getUserName()) != null) {
			throw new ExistingNameException("USER NAME : " + userDto.getUserName());
		}
		AppUser user = mapper.map(userDto, AppUser.class);
		user.setRoles(Arrays.asList(roleService.getRoleById(1)));
		user.setPassword(bcrypt.encode(userDto.getPassword()));

		return userRepo.save(user);
	}

	@Override
	public void addRole(String userName, AppRole role) {
		AppUser user = userRepo.findByUserName(userName);
		user.getRoles().add(role);
	}

	@Override
	public List<AppUser> getAllUser() {
		List<AppUser> listAllUser = userRepo.findAll();

		return listAllUser;
	}

	@Override
	public AppUser getUser(String userName) {

		return userRepo.findByUserName(userName);

	}

}
