package app.disney.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import app.disney.entitys.Gender;
import app.disney.repository.IGenderRepository;
import app.disney.service.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

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
		return genderRepo.findById(id).get();
	}

	@Override
	public void deleteGenderById(Integer id) {
		genderRepo.deleteById(id);
	}
	
	/***BUSQUEDAS***/

	@Override
	public Gender getByGenderName(String name) {
		return genderRepo.findByGenderName(name);
	}

}
