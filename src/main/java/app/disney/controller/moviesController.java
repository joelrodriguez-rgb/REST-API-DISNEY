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

import app.disney.DTO.GenderDTO;
import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Movie;
import app.disney.service.IGenderService;
import app.disney.service.IMovieService;
import app.disney.specification.MovieSpecification;

@Controller
public class moviesController {

	@Autowired
	private IMovieService movieService;

	@Autowired
	private IGenderService genderService;
	
	@Autowired
	private MovieSpecification spec;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/movies")
	public String listMovies(@RequestParam(value ="title", required = false) String title,
			                 @RequestParam(value ="gender", required = false) String genderName,
			                 Model model) {

		List<MovieDTO> listMovieDTO = movieService.mappingListToDTO(movieService.getAllMovie());
		List<GenderDTO> listGenderDTO = genderService.mappingListToDTO(genderService.getAllGender());
		
		if (title == null && genderName == null) {
			model.addAttribute("movies", listMovieDTO);
			model.addAttribute("genders", listGenderDTO );
		}else {
			GenderDTO gender = new GenderDTO(genderName);
			SearchMovieDTO searchMovie = new SearchMovieDTO(title,gender);
			Movie movieSpec = modelMapper.map(searchMovie, Movie.class);
			List<Movie> listMovieBySpec = movieService.getAllMovieBySpec(spec.getAllBySpec(movieSpec));
			
			model.addAttribute("movies", movieService.mappingListToDTO(listMovieBySpec));
			model.addAttribute("genders", listGenderDTO );
		}
		
		return "movies";

	}

	
	
	@GetMapping("/movies/addMovie")
	public String addMovie(ModelMap model) {

		MovieDTO movieDTO = new MovieDTO();

		model.addAttribute("movie", movieDTO);
		model.addAttribute("genders", genderService.mappingListToDTO(genderService.getAllGender()));

		return "addMovie";
	}

	
	
	@GetMapping("/movies/{id}")
	public String deleteMovie(@PathVariable Integer id) {

		movieService.deleteMovieById(id);

		return "redirect:/movies";
	}

	
	
	@GetMapping("/movies/editMovie/{id}")
	public String editMovie(@PathVariable Integer id, ModelMap model) {

		MovieDTO movieDTO = modelMapper.map(movieService.getMovieById(id), MovieDTO.class);

		model.addAttribute("movie", movieDTO);
		model.addAttribute("genders", genderService.mappingListToDTO(genderService.getAllGender()));

		return "editMovie";
	}
	
	@GetMapping("/movies/detailMovie/{id}")
	public String detailMovie(@PathVariable Integer id, ModelMap model) {
		
		MovieDTO movieDTO = modelMapper.map( movieService.getMovieById(id), MovieDTO.class);
		model.addAttribute("movie", movieDTO);
		return "detailMovie";
	}
		
		
	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") @Valid MovieDTO movieDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender", required = false) String genderName, Model model) {

		Movie movie = modelMapper.map(movieDTO, Movie.class);

		if (result.hasErrors()) {
			model.addAttribute("genders", genderService.mappingListToDTO(genderService.getAllGender()));
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

	
	
	
	@PostMapping("/editMovie/{id}")
	public String editMovie(@PathVariable Integer id, @ModelAttribute("movie") @Valid MovieDTO movieDTO,
			BindingResult result, @RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender", required = false) String genderName) {
		
		if (result.hasErrors()) {
			return "editMovie/{id}";
		}
		
		Movie existingMovie = movieService.getMovieById(id);
		
		if (genderName != null) {
			existingMovie.setGender(genderService.getByNameGender(genderName));
		}
		
		if (!imagen.isEmpty()) {
			
			movieService.saveImg(imagen);
			movieDTO.setImgMovie(imagen.getOriginalFilename());
			existingMovie.setImgMovie(movieDTO.getImgMovie());
		}
		
		existingMovie.setId(id);
		existingMovie.setCreationDate(movieDTO.getCreationDate());
		existingMovie.setQualification(movieDTO.getQualification());
		existingMovie.setTitle(movieDTO.getTitle());
		
		movieService.saveMovie(existingMovie);		
		
		return "redirect:/movies";
	}

}
