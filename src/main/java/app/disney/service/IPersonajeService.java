package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.PersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

public interface IPersonajeService  {

	/* FUNCIONES CRUD */

	void savePersonaje(PersonajeDTO newPersonaje);
	
	void upDatePersonaje(PersonajeDTO upPersonaje, Integer id);

	PersonajeDTO getPersonajeById(Integer id);

	void deletePersonajeById(Integer id);

	/* BUSQUEDAS */
	
	List<Personaje> getAllPersonaje();
	
	List<Personaje> getAllPersonaje(Specification<Personaje> spec);

	Personaje getByNameIgnoreCase(String name);
	
	List<String> getMovieByPersonajeId(Integer id);
	
	/////////////////////////////////////////////////
	
	void saveImg(Personaje personaje ,MultipartFile imagen);
	
	List<Movie> getListMovies(List<String> listMovieTitle);
	
    List<?> getList(String name, Integer year, Integer weight, String title);
    
    void validatePersonajeData(PersonajeDTO personajeData, MultipartFile imagen, List<String> listMovieTitle);


	

	



}
