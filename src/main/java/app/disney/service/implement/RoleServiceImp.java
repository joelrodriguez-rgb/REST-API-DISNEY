package app.disney.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.entitys.AppRole;
import app.disney.repository.IRoleRepository;
import app.disney.service.IRoleService;

@Service
public class RoleServiceImp implements IRoleService{
	
	@Autowired
	private IRoleRepository roleRepo;

	@Override
	public AppRole getRoleById(Integer id) {		
		return roleRepo.findById(id).get();
	}
	
	

	

}
