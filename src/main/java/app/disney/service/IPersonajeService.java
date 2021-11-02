package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import app.disney.entitys.Personaje;

public interface IPersonajeService {

	/* FUNCIONES CRUD */
	List<Personaje> getAllPersonaje();
	
	List<Personaje> getAllPersonaje(Specification<Personaje> spec);

	Personaje savePersonaje(Personaje Personaje);

	Personaje getPersonajeById(Integer id);

	void deletePersonajeById(Integer id);

	/* BUSQUEDAS */

	Personaje getByNameIgnoreCase(String name);
	
	List<String> getMovieByPersonajeId(Integer id);

	/* FILTROS */

	List<Personaje> getByYear(Integer year);
	
	List<Personaje> getByWeight(Double weight);
	
	List<Personaje> getByMovie(String title);

}
