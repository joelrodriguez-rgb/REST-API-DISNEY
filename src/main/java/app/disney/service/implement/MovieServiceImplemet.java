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

import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Movie;
import app.disney.exceptions.ExistingNameException;
import app.disney.exceptions.NotFoundException;
import app.disney.repository.IMovieRepository;
import app.disney.service.IGenderService;
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
	
	@Autowired
	private IGenderService genderService;



	/* FUNCIONES CRUD */
	@Override
	public List<Movie> getAllMovie() {
		return movieRepo.findAll();
	}

	@Override
	public void  saveMovie(MovieDTO newMovie, 
			               MultipartFile imagen, 
			               String gender) {
		Movie movie= mapping.mappingMovieDTOToEntity(newMovie);
		validateName(movie.getTitle());
		validateImagenAndListMovie(movie, imagen, gender);
		
		System.out.println("////////////////////////////////////////");
		movieRepo.save(movie);
	}
	
	
	
	
	@Override
	public void validateName(String title) {
		if (movieRepo.findByTitleIgnoreCase(title) != null) 
			throw new ExistingNameException("TITLE  : " + title);
	}
	
	@Override
	public void validateImagenAndListMovie(Movie movie, 
			                               MultipartFile imagen, 
			                               String gender) {
		
		if (imagen != null) saveImg(movie,imagen);
		if (gender != null) movie.setGender(genderService.getByNameGender(gender));
		
		
	}
	
	@Override
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).orElseThrow(() -> new NotFoundException("ID : " + id));
	}

	@Override
	public void deleteMovieById(Integer id) {
		
		if (movieRepo.findById(id).isPresent())
			movieRepo.deleteById(id);
		else
			throw new NotFoundException("ID : " + id);
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
	public void saveImg(Movie movie, MultipartFile imagen) {
		Path directorioImagenes = Paths.get("src//main//resources//static/imgMovies");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

		try {
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
			// SET Imagen
			movie.setImgMovie(imagen.getOriginalFilename());
			
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
