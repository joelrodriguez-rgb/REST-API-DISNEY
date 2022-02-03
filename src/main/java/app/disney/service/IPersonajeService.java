package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

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
	
	List<String> getMovieByPersonajeId(Integer id);
	
	/////////////////////////////////////////////////
	
	void saveImg(Personaje personaje ,MultipartFile imagen);
	
	List<Movie> getListMovies(List<String> listMovieTitle);
	
    List<?> getList(SearchPersonajeDTO searchPersonajeDTO);
    
    void validateImagenAndListMovie(Personaje personajeData, MultipartFile imagen, List<String> listMovieTitle);

    void validateName(String name);

	

	



}
