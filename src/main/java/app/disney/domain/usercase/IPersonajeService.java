package app.disney.domain.usercase;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;

public interface IPersonajeService  {

	/* FUNCIONES CRUD */

	void savePersonaje(PersonajeDTO newPersonaje, MultipartFile imagen, List<String> listMovieTitle);
	
	void upDatePersonaje(PersonajeDTO upPersonaje, Integer id,  MultipartFile imagen, List<String> listMovieTitle);

	Personaje getPersonajeById(Integer id);

	void deletePersonajeById(Integer id);

	/* BUSQUEDAS */
	
	List<Personaje> getAllPersonaje();
	
	List<Personaje> getAllPersonaje(Specification<Personaje> spec);

	Personaje getByNameIgnoreCase(String name);
	
	List<String> getAllMoviesByPersonajeId(Integer id);
	
	/////////////////////////////////////////////////
	
	String saveImg(MultipartFile imagen);
	
	List<Movie> getListMoviesByTitle(List<String> listMovieTitle);
	
    List<?> getListPersonajes(SearchPersonajeDTO searchPersonajeDTO);
    
    void validateName(String name);

	

	



}
