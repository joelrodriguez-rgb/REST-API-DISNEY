package app.disney.controller;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String listPersonaje( @RequestParam(value = "name", required = false) String name,
			                     @RequestParam(value = "year", required = false) Integer year,
			                     @RequestParam(value = "weight", required = false) Integer weight,
			                     @RequestParam(value = "title", required = false) String movieTitle,
			                      ModelMap model) {
	
		
		if (name == null &&
		    year == null &&
            weight == null &&
            movieTitle == null ) {
			model.addAttribute("personajes",personajeService.convertListToDTO(personajeService.getAllPersonaje()));
			model.addAttribute("movies",movieService.convertListToDTO(movieService.getAllMovie()));
		}else {
			    MovieDTO movieDTO = new MovieDTO(movieTitle);
			    SearchPersonajeDTO searchPersonajeDTO = new SearchPersonajeDTO(name, year, weight,movieDTO);
			    Personaje personajeSpec = modelMapper.map(searchPersonajeDTO, Personaje.class);
			    
				model.addAttribute("personajes",personajeService.convertListToDTO(personajeService.getAllPersonaje(spec.getAllBySpec(personajeSpec))));
				model.addAttribute("movies",movieService.convertListToDTO(movieService.getAllMovie()));
		}
		
		return "characters";
	}


	@GetMapping("/characters/addCharacter")
	public String addCharacter(ModelMap model) {

		PersonajeDTO personajeDTO = new PersonajeDTO();
		model.addAttribute("personaje", personajeDTO);
		model.addAttribute("movies",movieService.convertListToDTO(movieService.getAllMovie()));

		return "addCharacter";
	}

	@GetMapping("/characters/editCharacter/{id}")
	public String editCharacter(@PathVariable Integer id, ModelMap model) {
		
		PersonajeDTO personajeDTObyID = modelMapper.map(personajeService.getPersonajeById(id), PersonajeDTO.class);
		
		model.addAttribute("personaje", personajeDTObyID);
		model.addAttribute("movies",movieService.convertListToDTO(movieService.getAllMovie()));
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
	public String saveStudent(@ModelAttribute("personaje") @Valid PersonajeDTO personajeDTO
			                  ,BindingResult result
			                  ,@RequestParam(value = "file", required = false) MultipartFile imagen
			                  ,@RequestParam(value = "title", required = false) List<String> listMovieTitle) {
		
		Personaje personaje = modelMapper.map(personajeDTO, Personaje.class);

		if (result.hasErrors()) {
			return "addCharacter";
		}

		if (personajeService.getByNameIgnoreCase(personaje.getName()) != null) {
			return "/addCharacter";
		}

		if (!imagen.isEmpty()) {
			personajeService.saveImg(imagen);
			personaje.setImgPersonaje(imagen.getOriginalFilename());
		}
		

		personaje.setListMovie(personajeService.getListMovies(listMovieTitle));

		personajeService.savePersonaje(personaje);
		return "redirect:/characters";
	}

	@PostMapping("/editCharacter/{id}")
	public String saveChangesPersonaje(@PathVariable Integer id,
			                           @ModelAttribute("personaje") @Valid PersonajeDTO personajeDTO
			                           ,BindingResult result
			                           ,@RequestParam("title") List<String> listMovieTitle) {
		
		if (result.hasErrors()) {
			return "editCharacter/{id}";
		}

		personajeDTO.setListMovieDTO(movieService.convertListToDTO(personajeService.getListMovies(listMovieTitle)));
	
		Personaje personajeExisting = personajeService.getPersonajeById(id);
		personajeExisting.setId(id);
		personajeExisting.setName(personajeDTO.getName());
		personajeExisting.setYear(personajeDTO.getYear());
		personajeExisting.setWeight(personajeDTO.getWeight());
		personajeExisting.setListMovie(movieService.convertListToModel(personajeDTO.getListMovieDTO()));

		personajeService.savePersonaje(personajeExisting);
		return "redirect:/characters";
	}

}
