package app.disney.service.implement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.exceptions.NotFoundException;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;

@Service
public class PersonajeServiceImplement<T> implements IPersonajeService {
	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMovieService movieService;

	/** FUNCIONES CRUD */
	@Override
	public List<Personaje> getAllPersonaje() {
		return personajeRepo.findAll();
	}

	@Override
	public List<Personaje> getAllPersonaje(Specification<Personaje> spec) {
		return personajeRepo.findAll(spec);
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

	/** BUSQUEDA */
	@Override
	public Personaje getByNameIgnoreCase(String name) {
		return personajeRepo.findByNameIgnoreCase(name);
	}

	@Override
	public List<String> getMovieByPersonajeId(Integer id) {
		return personajeRepo.findMovieByPersonajeId(id);
	}

	////////////////////////////////////////
	@Override
	public void saveImg(MultipartFile imagen) {
		Path directorioImagenes = Paths.get("src//main//resources//static/imgCharacters");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

		try {
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Movie> getListMovies(List<String> listMovieTitle) {
		List<Movie> listMovie = new ArrayList<>();

		listMovieTitle.forEach(mov -> listMovie.add(movieService.getByTitleIgnoreCase(mov)));

		return listMovie;
	}

	public void validateId(Integer id) {

		if (personajeRepo.findById(id).isEmpty() || id == 0) {

			throw new NotFoundException("ID : " + id);
		}
	}

	
	
}
