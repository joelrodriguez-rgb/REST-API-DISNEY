package app.disney.service.implement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.exceptions.ExistingNameException;
import app.disney.exceptions.NotFoundException;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@Service
public class PersonajeServiceImplement implements IPersonajeService {
	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMovieService movieService;

	@Autowired
	private IMapper mapping;

	@Autowired
	private PersonajeSpecification spec;

	@Override
	public List<?> getListPersonajes(SearchPersonajeDTO searchPersonajeDTO) {

		if (searchPersonajeDTO.getName() == null &&
			searchPersonajeDTO.getYear() == null && 
			searchPersonajeDTO.getWeight() == null && 
			searchPersonajeDTO.getMovieDTO().getTitle() == null) {

			List<?> listPersonajeDTO = mapping.mappingListPersonajesToDTO(personajeRepo.findAll());
			return listPersonajeDTO;

		} else {
			Personaje personaje = mapping.mappingSearchPersonajeToEntity(searchPersonajeDTO);
			personaje.setListMovie(Arrays
					.asList(mapping.mappingMovieDTOToEntity(searchPersonajeDTO.getMovieDTO())));
			Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);
			List<Personaje> listPersonajeBySpec = personajeRepo.findAll(personajeSpec);
			List<?> list = mapping.mappingListPersonajesToDTO(listPersonajeBySpec);

			return list;

		}

	}

	@Override
	public List<Personaje> getAllPersonaje() {

		return personajeRepo.findAll();

	}

	@Override
	public List<Personaje> getAllPersonaje(Specification<Personaje> spec) {

		return personajeRepo.findAll(spec);

	}

	@Override
	public void savePersonaje(	PersonajeDTO newPersonaje,
								MultipartFile imagen,
								List<String> listMovieTitle) {

		Personaje personaje = mapping.mappingPersonajeDTOToEntity(newPersonaje);
		validateName(personaje.getName());

		personaje.setImgPersonaje(saveImg(imagen));
		personaje.setListMovie(getListMoviesByTitle(listMovieTitle));

		personajeRepo.save(personaje);

	}

	@Override
	public void upDatePersonaje(PersonajeDTO upPersonaje,
								Integer id,
								MultipartFile imagen,
								List<String> listMovieTitle) {

		Personaje personajeExisting = getPersonajeById(id);

		if (!personajeExisting.getName().equalsIgnoreCase(upPersonaje.getName())) {
			validateName(upPersonaje.getName());
		}
		personajeExisting.setId(id);
		personajeExisting.setName(upPersonaje.getName());
		personajeExisting.setYear(upPersonaje.getYear());
		personajeExisting.setWeight(upPersonaje.getWeight());
		personajeExisting.setImgPersonaje(saveImg(imagen));
		personajeExisting.setListMovie(getListMoviesByTitle(listMovieTitle));

		personajeRepo.save(personajeExisting);

	}

	@Override
	public void validateName(String name) {

		if (personajeRepo.findByNameIgnoreCase(name) != null)
			throw new ExistingNameException("NAME  : " + name);

	}

	@Override
	public Personaje getPersonajeById(Integer id) {

		return personajeRepo.findById(id).orElseThrow(() -> new NotFoundException("ID : " + id));

	}

	@Override
	public void deletePersonajeById(Integer id) {

		if (personajeRepo.findById(id).isPresent()) personajeRepo.deleteById(id);
		else throw new NotFoundException("ID : " + id);

	}

	@Override
	public Personaje getByNameIgnoreCase(String name) {

		return personajeRepo.findByNameIgnoreCase(name);

	}

	@Override
	public String saveImg(MultipartFile imagen) {

		if (imagen != null) {

			Path directorioImagenes = Paths.get("src//main//resources//static/imgCharacters");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				return imagen.getOriginalFilename();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public List<Movie> getListMoviesByTitle(List<String> listMovieTitle) {

		List<Movie> listMovie = new ArrayList<>();

		listMovieTitle.forEach(mov -> listMovie.add(movieService.getByTitleIgnoreCase(mov)));

		return listMovie;

	}

	@Override
	public List<String> getAllMoviesByPersonajeId(Integer id) {

		return personajeRepo.findAllMoviesByPersonajeId(id);

	}

}
