package app.disney.service;

import java.util.List;

import app.disney.entitys.Personaje;

public interface IPersonajeService {

	/* FUNCIONES CRUD */
	List<Personaje> getAllPersonaje();

	Personaje savePersonaje(Personaje Personaje);

	Personaje getPersonajeById(Integer id);

	void deletePersonajeById(Integer id);

	/* BUSQUEDAS */

	Personaje getFirstByPersonajeNameIgnoreCase(String name);
	
	List<String> getMovieByPersonajeId(Integer id);

	/* FILTROS */


}
