package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieRequest;
import app.disney.ports.input.rs.response.MovieDetailResponse;
import app.disney.ports.input.rs.response.MovieResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class MovieControllerMapperImpl implements MovieControllerMapper {

    @Override
    public Movie movieRequestToMovie(MovieRequest request) {
        if ( request == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setTitle( request.getTitle() );
        movie.setImgMovie( request.getImgMovie() );
        movie.setCreationDate( request.getCreationDate() );
        movie.setQualification( request.getQualification() );

        return movie;
    }

    @Override
    public List<MovieResponse> moviesToMoviesResponses(List<Movie> list) {
        if ( list == null ) {
            return null;
        }

        List<MovieResponse> list1 = new ArrayList<MovieResponse>( list.size() );
        for ( Movie movie : list ) {
            list1.add( movieToMovieResponse( movie ) );
        }

        return list1;
    }

    @Override
    public MovieDetailResponse movieToMovieDetailResponse(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDetailResponse movieDetailResponse = new MovieDetailResponse();

        movieDetailResponse.setTitle( movie.getTitle() );
        movieDetailResponse.setImgMovie( movie.getImgMovie() );
        movieDetailResponse.setCreationDate( movie.getCreationDate() );
        movieDetailResponse.setQualification( movie.getQualification() );
        movieDetailResponse.setGender( movie.getGender() );

        return movieDetailResponse;
    }

    protected MovieResponse movieToMovieResponse(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieResponse movieResponse = new MovieResponse();

        movieResponse.setTitle( movie.getTitle() );
        movieResponse.setImgMovie( movie.getImgMovie() );
        movieResponse.setCreationDate( movie.getCreationDate() );

        return movieResponse;
    }
}
