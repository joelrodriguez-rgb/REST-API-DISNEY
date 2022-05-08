package app.disney.ports.input.rs.controller;

import app.disney.domain.model.Movie;
import app.disney.domain.usercase.IMovieService;
import app.disney.ports.input.rs.mapper.MovieControllerMapper;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import app.disney.ports.input.rs.request.MovieRequest;
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
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;
    private final MovieControllerMapper mapper;

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> getMovies(@RequestBody(required = false) MovieFilterRequest request) {
        List<MovieResponse> listResponse;

        listResponse = mapper.moviesToMoviesResponses(movieService.getAllMovies(request));

        return ResponseEntity.ok().body(listResponse);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovieById(id);
    }

    @PostMapping(value = "/saveMovie")
    public ResponseEntity<Void> saveMovie(@Valid @RequestBody MovieRequest request) {

        Movie movie = mapper.movieRequestToMovie(request);
        final int id = movieService.saveMovie(movie);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/editMovie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editMovie(@Valid @NotNull @PathVariable Integer id,
                          @Valid @RequestBody MovieRequest request) {

        Movie upMovie = mapper.movieRequestToMovie(request);
        movieService.upDateMovie(id, upMovie);
    }

}
