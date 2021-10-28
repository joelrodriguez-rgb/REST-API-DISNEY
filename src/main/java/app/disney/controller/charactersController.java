package app.disney.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
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
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;

@Controller
public class charactersController {
	@Autowired
	private IPersonajeService personajeService;

	@Autowired
	private IMovieService movieService;

	@GetMapping("/characters" )
	public String listCharacter(ModelMap model) {
		Personaje buscarPersonaje = new Personaje();
		model.addAttribute("personajes", personajeService.getAllPersonaje());
		model.addAttribute("searchPersonajeName", buscarPersonaje);
		return "characters";
	}
	
	@GetMapping("/search")
	public String personajeName(@RequestParam("name")String name ,Model model) {
		model.addAttribute("personajes", personajeService.getByNameIgnoreCase(name));
		return "/characters";
	}

	@GetMapping("/characters/addCharacter")
	public String addCharacter(ModelMap model) {

		Personaje personaje = new Personaje();
		model.addAttribute("personaje", personaje);
		model.addAttribute("movies", movieService.getAllMovie());

		return "addCharacter";
	}

	@GetMapping("/characters/editCharacter/{id}")
	public String editCharacter(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("personaje", personajeService.getPersonajeById(id));
		model.addAttribute("movies", movieService.getAllMovie());
		return "editCharacter";
	}

	@GetMapping("/characters/{id}")
	public String deleteStudent(@PathVariable Integer id) {

		personajeService.deletePersonajeById(id);
		return "redirect:/characters";
	}
	
	
	@GetMapping("/characters/detailCharacter/{id}")
	public String detailCharacter(@PathVariable Integer id ,ModelMap model) {
		
		model.addAttribute("personaje", personajeService.getPersonajeById(id));
		model.addAttribute("moviesAsociate", personajeService.getMovieByPersonajeId(id));
		return"detailCharacter";
	}
	
	
	

	@PostMapping("/saveCharacter")
	public String saveStudent(@ModelAttribute("personaje") @Valid Personaje personaje
			, BindingResult result
			,@RequestParam("file") MultipartFile imagen
			,@RequestParam("title") List<String> listMovieTitle) {

		if (result.hasErrors()) {return "addCharacter";}

		if (personajeService.getByNameIgnoreCase(personaje.getName()) != null) {
			return "/addCharacter";}

		if (!imagen.isEmpty()) {saveImg(imagen);}
		personaje.setImgPersonaje(imagen.getOriginalFilename());
		
		
		personaje.setlistMovie(saveListMovies(listMovieTitle));
		
		
		personajeService.savePersonaje(personaje);
		return "redirect:/characters";
	}


	@PostMapping("/editCharacter/{id}")
	public String saveChangesPersonaje(@PathVariable Integer id
			,@ModelAttribute("personaje") Personaje personaje
			,@RequestParam("title") List<String> listMovieTitle) {
		

		
		personaje.setlistMovie(saveListMovies(listMovieTitle));
			
		
		Personaje personajeExisting = personajeService.getPersonajeById(id);
		personajeExisting.setId(id);
		personajeExisting.setName(personaje.getName());
		personajeExisting.setYear(personaje.getYear());
		personajeExisting.setWeight(personaje.getWeight());
		personajeExisting.setlistMovie(personaje.getlistMovie());
		
        
		personajeService.savePersonaje(personajeExisting);
		return "redirect:/characters";
	}
	
	
	
	public void saveImg(MultipartFile imagen) {
		Path directorioImagenes = Paths.get("src//main//resources/imgCharacters");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

		try {
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Movie> saveListMovies(List<String> listMovieTitle) {
		
		List<Movie> listMovieSave = new ArrayList<Movie>();
		listMovieTitle.forEach(mov -> listMovieSave.add(movieService.getByTitleIgnoreCase(mov)));
		
		return listMovieSave;
	}
}
