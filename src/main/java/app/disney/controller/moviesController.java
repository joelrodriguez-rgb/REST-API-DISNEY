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
import app.disney.entitys.Movie;
import app.disney.service.IGenderService;
import app.disney.service.IMovieService;

@Controller
public class moviesController {
	
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private IGenderService genderService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/movies")
	public String listMovies(Model model) {
		
		List<MovieDTO> listMovieDTO = movieService.mappingListToDTO(movieService.getAllMovie());
		
		model.addAttribute("movies", listMovieDTO);
		
		return "movies";
		
	}
	
	
	
	
	@GetMapping("/movies/addMovie")
	public String addMovie(ModelMap model) {
		
		MovieDTO movieDTO = new MovieDTO();
		
		model.addAttribute("movie", movieDTO);
		model.addAttribute("genders",  genderService.mappingListToDTO(genderService.getAllGender()));
		
		return "addMovie";
	}
	
	
	@GetMapping("/movies/{id}")
	public String deleteMovie(@PathVariable Integer id){
		
		movieService.deleteMovieById(id);
		
		return "redirect:/movies";
	}
	
	
	@GetMapping("/movies/editMovie/{id}")
	public String editMovie(@PathVariable Integer id, ModelMap model) {
		
		MovieDTO movieDTO = modelMapper.map(movieService.getMovieById(id), MovieDTO.class);
		
		model.addAttribute("movie", movieDTO);
		model.addAttribute("gender", genderService.mappingListToDTO(genderService.getAllGender()));
		
		return "editMovie";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") @Valid MovieDTO movieDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender", required = false) String genderName,
			Model model) {

		Movie movie = modelMapper.map(movieDTO, Movie.class);

		if (result.hasErrors()) {
			model.addAttribute("genders",  genderService.mappingListToDTO(genderService.getAllGender()));
			return "addMovie";
		}

		if (movieService.getByTitleIgnoreCase(movie.getTitle()) != null) {
			return "/addMovie";
		}

		if (!imagen.isEmpty()) {
			movieService.saveImg(imagen);
			movie.setImgMovie(imagen.getOriginalFilename());
		}

		if (genderName != null) {
			movie.setGender(genderService.getByNameGender(genderName));
		}

		movieService.saveMovie(movie);
		return "redirect:movies";
	}
	
	
	
	
	
	
	
	
	
	

}
