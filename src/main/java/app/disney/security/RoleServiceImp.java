package app.disney.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements IRoleService{
	
	@Autowired
	private IRoleRepository roleRepo;

	@Override
	public AppRole getRoleById(Integer id) {		
		return roleRepo.findById(id).get();
	}
	
	

	

}
