package app.disney.domain.usercase.impl;

import java.util.List;

import app.disney.common.exceptions.handler.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.domain.model.Gender;
import app.disney.domain.repository.IGenderRepository;
import app.disney.domain.usercase.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

	@Autowired
	private IGenderRepository genderRepo;
	


	@Override
	public List<Gender> getAllGender() {
		return genderRepo.findAll();
	}

	@Override
	public Gender saveGender(Gender gender) {
		return genderRepo.save(gender);
	}

	@Override
	public Gender getGenderById(Integer id) {
		return genderRepo.findById(id).orElseThrow(() -> new NotFoundException("ID : " + id));
	}

	@Override
	public void deleteGenderById(Integer id) {
		if (genderRepo.findById(id).isPresent())
			genderRepo.deleteById(id);
		else
			throw new NotFoundException("ID : " + id);
	}
	
	/***BUSQUEDAS***/

	@Override
	public Gender getByNameGender(String name) {
		return genderRepo.findByGenderName(name);
	}





}
