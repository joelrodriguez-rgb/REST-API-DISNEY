package app.disney.domain.usercase;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {

    Integer saveMovie(Movie request);

    void upDateMovie(Integer id, Movie upMovie);

    void deleteMovieById(Integer id);

    List<Movie> getAllMovies(MovieFilterRequest request,String order);

    List<Movie> getAllMovies(String order);

    void validateName(String title);
}
