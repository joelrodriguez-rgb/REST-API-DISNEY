package app.disney.domain.usercase.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.disney.ports.input.rs.request.MovieDTO;
import app.disney.ports.input.rs.request.SearchMovieDTO;
import app.disney.domain.model.Movie;
import app.disney.common.exceptions.ExistingNameException;
import app.disney.common.exceptions.NotFoundException;
import app.disney.domain.repository.IMovieRepository;
import app.disney.domain.usercase.IGenderService;
import app.disney.domain.usercase.IMovieService;
import app.disney.ports.input.rs.api.specification.MovieSpecification;
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

	@Override
	public List<?> getListMovies(SearchMovieDTO searchMovieDTO) {

		if (searchMovieDTO.getTitle() == null && searchMovieDTO.getGender() == null) {

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

	@Override
	public void saveMovie(MovieDTO newMovie, MultipartFile imagen, String gender) {

		Movie movie = mapping.mappingMovieDTOToEntity(newMovie);
		validateName(movie.getTitle());
		movie.setImgMovie(saveImg(imagen));
		movie.setGender(genderService.getByNameGender(gender));

		movieRepo.save(movie);

	}

	@Override
	public void upDateMovie(MovieDTO upMovie, Integer id, MultipartFile imagen, String gender) {

		Movie movieExisting = getMovieById(id);

		if (!movieExisting.getTitle().equalsIgnoreCase(upMovie.getTitle())) {
			validateName(upMovie.getTitle());
		}

		movieExisting.setId(id);
		movieExisting.setTitle(upMovie.getTitle());
		movieExisting.setCreationDate(upMovie.getCreationDate());
		movieExisting.setQualification(upMovie.getQualification());
		movieExisting.setImgMovie(saveImg(imagen));
		movieExisting.setGender(genderService.getByNameGender(gender));

		movieRepo.save(movieExisting);

	}

	@Override
	public void validateName(String title) {

		if (movieRepo.findByTitleIgnoreCase(title) != null)
			throw new ExistingNameException("TITLE  : " + title);

	}

	@Override
	public Movie getMovieById(Integer id) {

		return movieRepo.findById(id).orElseThrow(() -> new NotFoundException("ID : " + id));

	}

	@Override
	public void deleteMovieById(Integer id) {

		if (movieRepo.findById(id).isPresent()) movieRepo.deleteById(id);
		else throw new NotFoundException("ID : " + id);

	}

	@Override
	public Movie getByTitleIgnoreCase(String title) {

		return movieRepo.findByTitleIgnoreCase(title);

	}

	@Override
	public String saveImg(MultipartFile imagen) {

		if (imagen != null) {

			Path directorioImagenes = Paths.get("src//main//resources//static/imgMovies");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				return imagen.getOriginalFilename();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public List<Movie> getAllMovieBySpec(Specification<Movie> spec) {

		return movieRepo.findAll(spec);

	}

	@Override
	public List<?> getAllOrderByCreationDateAsc() {

		List<?> list = mapping.mappingListMovie(movieRepo.findAllOrderByCreationDateAsc());

		return list;

	}

	@Override
	public List<?> getAllOrderByCreationDateDesc() {

		List<?> list = mapping.mappingListMovie(movieRepo.findAllOrderByCreationDateDesc());

		return list;

	}

	@Override
	public List<String> getAllPersonajesByMovie(Integer id) {

		List<String> listPersonaje = movieRepo.findAllPersonajesByMovie(id);

		return listPersonaje;

	}

}
