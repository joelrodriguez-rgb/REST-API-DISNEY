package app.disney.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements IRoleService{
	
	@Autowired
	private IRoleRepository roleRepo;

	@Override
	public AppRole getRoleById(Integer id) {
		return roleRepo.getById(id);
	}
	
	

	

}
