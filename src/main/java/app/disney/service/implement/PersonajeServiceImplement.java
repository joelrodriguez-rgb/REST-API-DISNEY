package app.disney.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.entitys.Personaje;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IPersonajeService;

@Service
public class PersonajeServiceImplement implements IPersonajeService {
	@Autowired
	private IPersonajeRepository personajeRepo;

	/** FUNCIONES CRUD */
	@Override
	public List<Personaje> getAllPersonaje() {
		return personajeRepo.findAll();
	}

	@Override
	public Personaje savePersonaje(Personaje Personaje) {
		return personajeRepo.save(Personaje);
	}

	@Override
	public Personaje getPersonajeById(Integer id) {
		return personajeRepo.findById(id).get();
	}

	@Override
	public void deletePersonajeById(Integer id) {
		personajeRepo.deleteById(id);
	}

	/**BUSQUEDA */
	@Override
	public Personaje getByNameIgnoreCase(String name) {
		return personajeRepo.findByNameIgnoreCase(name);
	}

	@Override
	public List<String> getMovieByPersonajeId(Integer id) {
		return personajeRepo.findMovieByPersonajeId(id);
	}


	/** FILTROS */
	
	@Override
	public List<Personaje> getByYear(Integer year) {
		return personajeRepo.findByYear(year);
	}

	@Override
	public List<Personaje> getByWeight(Double weight) {
		return personajeRepo.findByWeight(weight);
	}

	@Override
	public List<Personaje> getByMovie(String title) {
		return personajeRepo.findByMovie(title);
	}

}
