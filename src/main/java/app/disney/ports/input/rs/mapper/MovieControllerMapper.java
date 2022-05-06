package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieRequest;
import app.disney.ports.input.rs.response.MovieResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MovieControllerMapper extends CommonMapper {

    Movie movieRequestToMovie (MovieRequest request);

    MovieResponse movieToMovieResponse (Movie movie);

    List<MovieResponse> moviesToMoviesResponses(List<Movie> list);

}
