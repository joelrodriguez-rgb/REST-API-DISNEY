package app.disney.service;

import java.util.List;

import app.disney.DTO.MovieDTO;
import app.disney.entitys.Movie;

public interface IMovieService {
	
	/* FUNCIONES CRUD */
	List<Movie> getAllMovie();

	Movie saveMovie(Movie movie);

	Movie getMovieById(Integer id);

	void deleteMovieById(Integer id);
	
	/* BUSQUEDAS */
	Movie getByTitleIgnoreCase(String title);
	
	/*FILTROS*/
	
	List<Movie> getByGender(String gender);
	
	/////////////////////////////////////////
	List<MovieDTO> convertListToDTO (List<Movie> listMovies);
	
	List<Movie> convertListToModel(List<MovieDTO> listMovieDTO);

}
