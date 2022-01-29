package app.disney.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Personaje;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@RestController
@RequestMapping("/characters")
public class charactersController<T> {
	@Autowired
	private IMapper mapping;

	@Autowired
	private IPersonajeService personajeService;

	@Autowired
	private IMovieService movieService;

	@Autowired
	private PersonajeSpecification spec;

	@GetMapping()
	public ResponseEntity<List<?>> listPersonaje(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "weight", required = false) Integer weight,
			@RequestParam(value = "title", required = false) String title) {

		List<?> listPersonajeDTO = mapping.mappingListPersonajesToDTO(personajeService.getAllPersonaje());

		if (name == null && year == null && weight == null && title == null) {

			return new ResponseEntity<>(listPersonajeDTO, HttpStatus.OK);

		} else {
			MovieDTO movieDTO = new MovieDTO(title);
			SearchPersonajeDTO searchPersonajeDTO = new SearchPersonajeDTO(name, year, weight, movieDTO);
			Personaje personajeSpec = mapping.mappingSearchPersonajeToEntity(searchPersonajeDTO);
			List<Personaje> listPersonajeBySpec = personajeService.getAllPersonaje(spec.getAllBySpec(personajeSpec));
			List<?> list = mapping.mappingListPersonajesToDTO(listPersonajeBySpec);

			return new ResponseEntity<>(list, HttpStatus.OK);

		}
	}

	@GetMapping("/addCharacter")
	public ResponseEntity<?> addCharacter() {

		PersonajeDTO personajeDTO = new PersonajeDTO();
		List<?> listMovie = mapping.mappingListMovie(movieService.getAllMovie());

		HashMap<String, Object> map = new HashMap<>();
		map.put("personaje", personajeDTO);
		map.put("movies", listMovie);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/editCharacter/{id}")
	public ResponseEntity<?> editCharacter(@PathVariable Integer id) {

		PersonajeDTO personajeDTObyID = mapping.mappingPersonajeToDTO(personajeService.getPersonajeById(id));
		List<?> listMovie = mapping.mappingListMovie(movieService.getAllMovie());

		HashMap<String, Object> map = new HashMap<>();
		map.put("personaje", personajeDTObyID);
		map.put("movies", listMovie);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<List<?>> deletePersonaje(@PathVariable Integer id) {
		personajeService.deletePersonajeById(id);
		List<?> listPersonajeDTO = mapping.mappingListPersonajesToDTO(personajeService.getAllPersonaje());
		return new ResponseEntity<List<?>>(listPersonajeDTO, HttpStatus.OK);
	}

	@GetMapping("/detailCharacter/{id}")
	public ResponseEntity<?> detailCharacter(@PathVariable Integer id) throws  {

		
		personajeService.validateId(id);
		
		PersonajeDTO personajeDTObyID = mapping.mappingPersonajeToDTO(personajeService.getPersonajeById(id));
		List<String> listMovie = personajeService.getMovieByPersonajeId(id);

		HashMap<String, Object> map = new HashMap<>();
		map.put("personaje", personajeDTObyID);
		map.put("movies", listMovie);

		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@PostMapping("/saveCharacter")
	public ResponseEntity<?> saveStudent(@RequestBody @Valid PersonajeDTO personajeDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "title", required = false) List<String> listMovieTitle) {

		Personaje personaje = mapping.mappingPersonajeDTOToEntity(personajeDTO);
		List<?> listMovie = mapping.mappingListMovie(movieService.getAllMovie());

		if (result.hasErrors()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("personaje", personaje);
			map.put("movies", listMovie);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		if (personajeService.getByNameIgnoreCase(personaje.getName()) != null) {
			return new ResponseEntity<>(personaje, HttpStatus.BAD_REQUEST);
		}

		if (imagen != null) {
			personajeService.saveImg(imagen);
			personaje.setImgPersonaje(imagen.getOriginalFilename());
		}

		if (listMovieTitle != null) {
			personaje.setListMovie(personajeService.getListMovies(listMovieTitle));
		}

		personajeService.savePersonaje(personaje);
		return new ResponseEntity<>(personaje, HttpStatus.CREATED);

	}

	@PatchMapping("/editCharacter/{id}")
	public ResponseEntity<?> saveChangesPersonaje(@PathVariable Integer id,
			@RequestBody @Valid PersonajeDTO personajeDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "title", required = false) List<String> listMovieTitle) {

		Personaje personajeExisting = personajeService.getPersonajeById(id);
		List<?> listMovie = mapping.mappingListMovie(movieService.getAllMovie());

		if (result.hasErrors()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("personaje", personajeDTO);
			map.put("movies", listMovie);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		if (listMovieTitle != null) {
			personajeExisting.setListMovie(personajeService.getListMovies(listMovieTitle));
		}

		if (!imagen.isEmpty()) {
			personajeService.saveImg(imagen);
			personajeExisting.setImgPersonaje(imagen.getOriginalFilename());
		}

		personajeExisting.setId(id);
		personajeExisting.setName(personajeDTO.getName());
		personajeExisting.setYear(personajeDTO.getYear());
		personajeExisting.setWeight(personajeDTO.getWeight());

		personajeService.savePersonaje(personajeExisting);

		return new ResponseEntity<>(mapping.mappingPersonajeToDTO(personajeExisting), HttpStatus.UPGRADE_REQUIRED);
	}

}
