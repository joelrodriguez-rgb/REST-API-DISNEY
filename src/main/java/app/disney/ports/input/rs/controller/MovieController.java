package app.disney.ports.input.rs.controller;

import app.disney.domain.model.Movie;
import app.disney.domain.usercase.IMovieService;
import app.disney.ports.api.ApiConstants;
import app.disney.ports.input.rs.mapper.MovieControllerMapper;
import app.disney.ports.input.rs.mapper.PersonajeControllerMapper;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import app.disney.ports.input.rs.request.MovieRequest;
import app.disney.ports.input.rs.response.MovieDetailResponse;
import app.disney.ports.input.rs.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(ApiConstants.MOVIES_URI)
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    private final MovieControllerMapper mapper;

    private final PersonajeControllerMapper mapperPerson;

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> getMovies(@RequestParam(required = false) String title,
                                                         @RequestParam(required = false) Long idGender,
                                                         @RequestParam(required = false) String order) {

        MovieFilterRequest request = new MovieFilterRequest(title, idGender, order);
        List<MovieResponse> listResponse;

        {
            if (request.getTitle() != null || request.getIdGender() != null)
                listResponse = mapper.moviesToMoviesResponses(movieService.getAllMoviesByFilter(request));
            else
                listResponse = mapper.moviesToMoviesResponses(movieService.getAllMovies(request));
        }

        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailResponse> getMovie(@Valid @NotNull @PathVariable Long id) {

        MovieDetailResponse response = mapper.movieToMovieDetailResponse(movieService.getMovie(id));

        List<String> listPersonajes = movieService.getPersonajesByMovie(id);
        response.setPersonajes(listPersonajes);

        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
    }

    @PostMapping(value = "/saveMovie")
    public ResponseEntity<Void> saveMovie(@Valid @RequestBody MovieRequest request) {

        Movie movie = mapper.movieRequestToMovie(request);
        final long id = movieService.saveMovie(movie);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/editMovie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editMovie(@Valid @NotNull @PathVariable Long id,
                          @Valid @RequestBody MovieRequest request) {

        Movie upMovie = mapper.movieRequestToMovie(request);
        movieService.upDateMovie(id, upMovie);
    }

}
