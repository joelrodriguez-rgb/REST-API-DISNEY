package app.disney.domain.usercase;

import java.util.List;

import app.disney.domain.model.Gender;

public interface IGenderService {
	
	/* FUNCIONES CRUD */
	List<Gender> getAllGender();

	Gender saveGender(Gender gender);
	
	Gender getGenderById(Integer id);

	void deleteGenderById(Integer id);
	
	/* BUSQUEDAS*/
	Gender getByNameGender(String name);
	
	

}
