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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.GenderDTO;
import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Movie;
import app.disney.service.IGenderService;
import app.disney.service.IMovieService;
import app.disney.specification.MovieSpecification;
import app.disney.util.IMapper;

@RestController
@RequestMapping("/movies")
public class moviesController {

	@Autowired
	private IMovieService movieService;

	@Autowired
	private IGenderService genderService;

	@Autowired
	private MovieSpecification spec;

	@Autowired
	private IMapper mapping;

	@GetMapping()
	public ResponseEntity<List<?>> listMovies(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "gender", required = false) String genderName) {

		GenderDTO gender = new GenderDTO(genderName);
		SearchMovieDTO searchMovieDTO = new SearchMovieDTO(title, gender);
		List<?> listMovies = movieService.getList(searchMovieDTO);
		
		return new ResponseEntity<List<?>>(listMovies, HttpStatus.OK);

	}

	@GetMapping("/addMovie")
	public ResponseEntity<?> addMovie() {

		MovieDTO movieDTO = new MovieDTO();
		return new ResponseEntity<>(movieDTO, HttpStatus.OK);

	}

	@GetMapping("/editMovie/{id}")
	public ResponseEntity<?> editMovie(@PathVariable Integer id) {

		MovieDTO movieDTO = mapping.mappingMovieToDTO(movieService.getMovieById(id));

		return new ResponseEntity<>(movieDTO, HttpStatus.OK);
	}

	@GetMapping("/detailMovie/{id}")
	public ResponseEntity<?> detailMovie(@PathVariable Integer id) {

		MovieDTO movieDTO = mapping.mappingMovieToDTO(movieService.getMovieById(id));
		return new ResponseEntity<>(movieDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<List<?>> deleteMovie(@PathVariable Integer id) {
		movieService.deleteMovieById(id);
		List<?> list = mapping.mappingListMovie(movieService.getAllMovie());

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveMovie(@ModelAttribute("movie") @Valid MovieDTO movieDTO, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender", required = false) String genderName) {

		Movie movie = mapping.mappingMovieDTOToEntity(movieDTO);
		List<?> listGender = mapping.mappingListGender(genderService.getAllGender());
		
		if (result.hasErrors()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("movie", movie);
			map.put("genders", listGender);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}

		if (movieService.getByTitleIgnoreCase(movie.getTitle()) != null) {
			return new ResponseEntity<>(movie, HttpStatus.BAD_REQUEST);
		}

		if (!imagen.isEmpty()) {
			movieService.saveImg(imagen);
			movie.setImgMovie(imagen.getOriginalFilename());
		}

		if (genderName != null) {
			movie.setGender(genderService.getByNameGender(genderName));
		}

		movieService.saveMovie(movie);
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	@PatchMapping("/editMovie/{id}")
	public ResponseEntity<?> editMovie(@PathVariable Integer id, @ModelAttribute("movie") @Valid MovieDTO movieDTO,
			BindingResult result, @RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender", required = false) String genderName) {

		
		Movie existingMovie = movieService.getMovieById(id);
		List<?> listGender = mapping.mappingListGender(genderService.getAllGender());
		
		if (result.hasErrors()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("movie", movieDTO);
			map.put("genders", listGender);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}


		if (genderName != null) {
			existingMovie.setGender(genderService.getByNameGender(genderName));
		}

		if (!imagen.isEmpty()) {

			movieService.saveImg(imagen);
			existingMovie.setImgMovie(imagen.getOriginalFilename());
		}

		existingMovie.setId(id);
		existingMovie.setCreationDate(movieDTO.getCreationDate());
		existingMovie.setQualification(movieDTO.getQualification());
		existingMovie.setTitle(movieDTO.getTitle());

		movieService.saveMovie(existingMovie);

		return new ResponseEntity<>(mapping.mappingMovieToDTO(existingMovie), HttpStatus.UPGRADE_REQUIRED);
	}

}
