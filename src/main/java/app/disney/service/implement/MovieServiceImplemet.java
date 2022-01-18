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

import app.disney.entitys.Movie;
import app.disney.repository.IMovieRepository;
import app.disney.service.IMovieService;

@Service
public class MovieServiceImplemet implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;



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
          return movieRepository.findAll(spec);
	}

}
