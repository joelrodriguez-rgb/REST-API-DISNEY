package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

public interface IPersonajeService  {

	/* FUNCIONES CRUD */

	Personaje savePersonaje(Personaje Personaje);

	Personaje getPersonajeById(Integer id);

	void deletePersonajeById(Integer id);

	/* BUSQUEDAS */
	
	List<Personaje> getAllPersonaje();
	
	List<Personaje> getAllPersonaje(Specification<Personaje> spec);

	Personaje getByNameIgnoreCase(String name);
	
	List<String> getMovieByPersonajeId(Integer id);
	
	/////////////////////////////////////////////////
	
	void saveImg(MultipartFile imagen);
	
	List<Movie> getListMovies(List<String> listMovieTitle);
	

	
	public void validationID(Integer id); 



	

	



}
