package app.disney.service;

import java.util.List;

import app.disney.entitys.Gender;

public interface IGenderService {
	
	/* FUNCIONES CRUD */
	List<Gender> getAllGender();

	Gender saveGender(Gender gender);
	
	Gender getGenderById(Integer id);

	void deleteGenderById(Integer id);
	
	/* BUSQUEDAS*/
	Gender getByGenderName(String name);

}
