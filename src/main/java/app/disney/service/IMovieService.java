package app.disney.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

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
	
	List<Movie> getAllMovieBySpec(Specification<Movie> spec);
	
	/*FILTROS*/
	
	List<Movie> getByGender(String gender);

	
	
	/////////////////////////////////////////
	
	void saveImg(MultipartFile imagen);
	

	List<Movie> mappingListToModel(List<MovieDTO> listDTO);

	List<MovieDTO> mappingListToDTO(List<Movie> listModel);

	


}
