package app.disney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.GenderDTO;
import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.service.IMovieService;
import app.disney.util.IMapper;

@RestController
@RequestMapping("/movies")
public class moviesController {

	@Autowired
	private IMovieService movieService;

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
	public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
		
		movieService.deleteMovieById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/saveMovie")
	public ResponseEntity<?> saveMovie(
			@RequestBody @Valid MovieDTO newMovie,
			BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender") String genderName) {

		if (result.hasErrors()) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		
		movieService.saveMovie(newMovie, imagen, genderName);
	
		return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
	}

	@PutMapping("/editMovie/{id}")
	public ResponseEntity<?> editMovie(
			@PathVariable Integer id,
			@RequestBody @Valid MovieDTO upMovie,
			BindingResult result, 
			@RequestParam(value = "file", required = false) MultipartFile imagen,
			@RequestParam(value = "gender") String genderName) {
		
		if (result.hasErrors()) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

		movieService.upDateMovie(upMovie, id, imagen, genderName);

		return new ResponseEntity<>(upMovie, HttpStatus.OK);
	}

}
