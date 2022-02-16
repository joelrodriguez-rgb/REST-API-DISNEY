package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Movie;

public interface IMovieService {

	/* FUNCIONES CRUD */

	void saveMovie(MovieDTO newMovie, MultipartFile imagen, String gender);

	void upDateMovie(MovieDTO upMovie, Integer id, MultipartFile imagen, String gender);

	Movie getMovieById(Integer id);

	void deleteMovieById(Integer id);

	List<?> getListMovies(SearchMovieDTO searchMovieDTO);

	/* BUSQUEDAS */
	
	Movie getByTitleIgnoreCase(String title);

	List<Movie> getAllMovieBySpec(Specification<Movie> spec);
	
	List<String> getAllPersonajesByMovie(Integer id);

	/* FILTROS */

	List<?> getAllOrderByCreationDateAsc();

	List<?> getAllOrderByCreationDateDesc();

	/////////////////////////////////////////

	String saveImg(MultipartFile imagen);

	void validateName(String title);

}
