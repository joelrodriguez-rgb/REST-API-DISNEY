package app.disney.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Personaje;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;
import app.disney.specification.PersonajeSpecification;

@Controller
public class charactersController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPersonajeService personajeService;

	@Autowired
	private IMovieService movieService;

	@Autowired
	private PersonajeSpecification spec;

	@GetMapping("/characters")
	public String listPersonaje(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "weight", required = false) Integer weight,
			@RequestParam(value = "id", required = false) Integer idMovie, ModelMap model) {
		
		List<MovieDTO> listMovieDTO = movieService.mappingListToDTO(movieService.getAllMovie());

		if (name == null && year == null && weight == null && idMovie == null) {
			model.addAttribute("personajes", personajeService.mappingListToDTO(personajeService.getAllPersonaje()));
			model.addAttribute("movies", listMovieDTO);
		} else {
			MovieDTO movieDTO =  modelMapper.map(movieService.getMovieById(idMovie), MovieDTO.class);
			SearchPersonajeDTO searchPersonajeDTO = new SearchPersonajeDTO(name, year, weight, movieDTO);
			Personaje personajeSpec = modelMapper.map(searchPersonajeDTO, Personaje.class);
			List<Personaje> listPersonajeBySpec = personajeService.getAllPersonaje(spec.getAllBySpec(personajeSpec));

			model.addAttribute("personajes", personajeService.mappingListToDTO(listPersonajeBySpec));
			model.addAttribute("movies",listMovieDTO);
		}

		return "characters";
	}

	@GetMapping("/characters/addCharacter")
	public String addCharacter(ModelMap model) {

		PersonajeDTO personajeDTO = new PersonajeDTO();
		model.addAttribute("personaje", personajeDTO);
		model.addAttribute("movies", movieService.mappingListToDTO(movieService.getAllMovie()));

		return "addCharacter";
	}

	@GetMapping("/characters/editCharacter/{id}")
	public String editCharacter(@PathVariable Integer id, ModelMap model) {

		PersonajeDTO personajeDTObyID = modelMapper.map(personajeService.getPersonajeById(id), PersonajeDTO.class);

		model.addAttribute("personaje", personajeDTObyID);
		model.addAttribute("movies", movieService.mappingListToDTO(movieService.getAllMovie()));
		return "editCharacter";
	}

	@GetMapping("/characters/{id}")
	public String deleteStudent(@PathVariable Integer id) {

		personajeService.deletePersonajeById(id);
		return "redirect:/characters";
	}

	@GetMapping("/characters/detailCharacter/{id}")
	public String detailCharacter(@PathVariable Integer id, ModelMap model) {

		model.addAttribute("personaje", personajeService.getPersonajeById(id));
		model.addAttribute("moviesAsociate", personajeService.getMovieByPersonajeId(id));
		return "detailCharacter";
	}

	@PostMapping("/saveCharacter")
	public String saveStudent(@ModelAttribute("personaje") @Valid PersonajeDTO personajeDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "title", required = false) List<String> listMovieTitle,
			Model model) {

		Personaje personaje = modelMapper.map(personajeDTO, Personaje.class);

		if (result.hasErrors()) {
			model.addAttribute("movies",  movieService.mappingListToDTO(movieService.getAllMovie()));
			return "addCharacter";
		}

		if (personajeService.getByNameIgnoreCase(personaje.getName()) != null) {
			return "/addCharacter";
		}

		if (!imagen.isEmpty()) {
			personajeService.saveImg(imagen);
			personaje.setImgPersonaje(imagen.getOriginalFilename());
		}

		if (listMovieTitle != null) {
			personaje.setListMovie(personajeService.getListMovies(listMovieTitle));
		}

		personajeService.savePersonaje(personaje);
		return "redirect:/characters";
	}

	@PostMapping("/editCharacter/{id}")
	public String saveChangesPersonaje(@PathVariable Integer id,
			@ModelAttribute("personaje") @Valid PersonajeDTO personajeDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "title", required = false) List<String> listMovieTitle) {

		if (result.hasErrors()) {
			return "editCharacter/{id}";
		}
		
		Personaje personajeExisting = personajeService.getPersonajeById(id);

		if (listMovieTitle != null) {
			personajeDTO.setListMovieDTO(movieService.mappingListToDTO(personajeService.getListMovies(listMovieTitle)));
			personajeExisting.setListMovie(movieService.mappingListToModel(personajeDTO.getListMovieDTO()));
		}

		if (!imagen.isEmpty()) {
			personajeService.saveImg(imagen);
			personajeDTO.setImgPersonaje(imagen.getOriginalFilename());
			personajeExisting.setImgPersonaje(personajeDTO.getImgPersonaje());
		}

		personajeExisting.setId(id);
		personajeExisting.setName(personajeDTO.getName());
		personajeExisting.setYear(personajeDTO.getYear());
		personajeExisting.setWeight(personajeDTO.getWeight());

		personajeService.savePersonaje(personajeExisting);
		return "redirect:/characters";
	}

}
