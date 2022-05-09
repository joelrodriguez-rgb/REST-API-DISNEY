package app.disney.domain.usercase;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {

    Integer saveMovie(Movie request);

    void upDateMovie(Integer id, Movie upMovie);

    void deleteMovieById(Integer id);

    List<Movie> getAllMoviesByFilter(MovieFilterRequest request);

    List<Movie> getAllMovies(MovieFilterRequest request);
    void validateName(String title);
}
