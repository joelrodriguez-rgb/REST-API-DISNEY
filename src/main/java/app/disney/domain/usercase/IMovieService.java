package app.disney.domain.usercase;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {

    Long saveMovie(Movie request);

    void upDateMovie(Long id, Movie upMovie);

    void deleteMovieById(Long id);

    List<Movie> getAllMoviesByFilter(MovieFilterRequest request);

    List<Movie> getAllMovies(MovieFilterRequest request);

    Movie getMovie (Long id);

    List<String> getPersonajesByMovie (Long id);
}
