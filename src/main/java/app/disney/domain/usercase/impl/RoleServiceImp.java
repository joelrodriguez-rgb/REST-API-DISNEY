package app.disney.domain.usercase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.domain.model.AppRole;
import app.disney.domain.repository.IRoleRepository;
import app.disney.domain.usercase.IRoleService;

@Service
public class RoleServiceImp implements IRoleService{
	
	@Autowired
	private IRoleRepository roleRepo;

	@Override
	public AppRole getRoleById(Integer id) {		
		return roleRepo.findById(id).get();
	}
	
	

	

}
