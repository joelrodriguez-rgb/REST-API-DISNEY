package app.disney.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.DTO.MovieDTO;
import app.disney.entitys.Movie;
import app.disney.repository.IMovieRepository;
import app.disney.service.IMovieService;

@Service
public class MovieServiceImplemet implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Autowired
	private ModelMapper modelMapper;

	/* FUNCIONES CRUD */
	@Override
	public List<Movie> getAllMovie() {
		return movieRepository.findAll();
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie getMovieById(Integer id) {
		return movieRepository.findById(id).get();
	}

	@Override
	public void deleteMovieById(Integer id) {
		movieRepository.deleteById(id);
	}

	/* BUSQUEDAS */

	@Override
	public Movie getByTitleIgnoreCase(String title) {
		return movieRepository.findByTitleIgnoreCase(title);
	}

	/* FILTROS */

	@Override
	public List<Movie> getByGender(String gender) {
		return movieRepository.findByGender(gender);
	}

	///////////////////////////////////////////////////////////////
	
	
	
	
	
	
	@Override
	public List<MovieDTO> mappingListToDTO(List<Movie> listModel) {
		List<MovieDTO> listMovieModel = listModel
                                        .stream()
                                        .map(movies -> modelMapper.map(movies, MovieDTO.class))
                                        .collect(Collectors.toList());
        return listMovieModel;
}
	
	@Override
	public List<Movie> mappingListToModel(List<MovieDTO> listDTO) {
		List<Movie> listMovieModel =  listDTO
                                     .stream()
                                     .map(movies -> modelMapper.map(movies, Movie.class))
                                     .collect(Collectors.toList());
        return listMovieModel;
	}
	


}
