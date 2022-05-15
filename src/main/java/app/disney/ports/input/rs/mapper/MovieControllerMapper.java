package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieRequest;
import app.disney.ports.input.rs.response.MovieDetailResponse;
import app.disney.ports.input.rs.response.MovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieControllerMapper extends CommonMapper {

    @Mapping(source = "idGender", target = "gender.id")
    Movie movieRequestToMovie(MovieRequest request);

    List<MovieResponse> moviesToMoviesResponses(List<Movie> list);

    MovieDetailResponse movieToMovieDetailResponse(Movie movie);

}
