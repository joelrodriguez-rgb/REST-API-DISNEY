package app.disney.service.implement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Movie;
import app.disney.repository.IMovieRepository;
import app.disney.service.IMovieService;
import app.disney.specification.MovieSpecification;
import app.disney.util.IMapper;

@Service
public class MovieServiceImplemet implements IMovieService {

	@Autowired
	private IMovieRepository movieRepo;
	
	@Autowired
	private IMapper mapping;

	@Autowired
	private MovieSpecification spec;



	/* FUNCIONES CRUD */
	@Override
	public List<Movie> getAllMovie() {
		return movieRepo.findAll();
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return movieRepo.save(movie);
	}

	@Override
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).get();
	}

	@Override
	public void deleteMovieById(Integer id) {
		movieRepo.deleteById(id);
	}

	/* BUSQUEDAS */

	@Override
	public Movie getByTitleIgnoreCase(String title) {
		return movieRepo.findByTitleIgnoreCase(title);
	}

	/* FILTROS */

	@Override
	public List<Movie> getByGender(String gender) {
		return movieRepo.findByGender(gender);
	}

	///////////////////////////////////////////////////////////////
	@Override
	public void saveImg(MultipartFile imagen) {
		Path directorioImagenes = Paths.get("src//main//resources//static/imgMovies");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

		try {
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public List<Movie> getAllMovieBySpec(Specification<Movie> spec) {
          return movieRepo.findAll(spec);
	}

	@Override
	public List<?> getList(SearchMovieDTO searchMovieDTO) {
		

		if (searchMovieDTO.getTitle() == null && 
			searchMovieDTO.getGender() == null) {
			
			List<?> listMovieDTO = mapping.mappingListMovie(movieRepo.findAll());
			return listMovieDTO;
			
		} else {

			Movie movie = mapping.mappingSearchMovieToEntity(searchMovieDTO);
			Specification<Movie> movieSpec = spec.getAllBySpec(movie);
			List<Movie> listMovieBySpec = movieRepo.findAll(movieSpec);
			List<?> list = mapping.mappingListMovie(listMovieBySpec);

			return list;

		}
	}

}
