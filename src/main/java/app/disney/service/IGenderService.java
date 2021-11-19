package app.disney.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.GenderDTO;
import app.disney.entitys.Gender;

public interface IGenderService {
	
	/* FUNCIONES CRUD */
	List<Gender> getAllGender();

	Gender saveGender(Gender gender);
	
	Gender getGenderById(Integer id);

	void deleteGenderById(Integer id);
	
	/* BUSQUEDAS*/
	Gender getByNameGender(String name);
	
	
	List<Gender> mappingListToModel(List<GenderDTO> listDTO);

	List<GenderDTO> mappingListToDTO(List<Gender> listModel);

}
