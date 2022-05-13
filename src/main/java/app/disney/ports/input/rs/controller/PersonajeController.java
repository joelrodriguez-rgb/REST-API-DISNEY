package app.disney.ports.input.rs.controller;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.domain.usercase.IPersonajeService;
import app.disney.ports.api.ApiConstants;
import app.disney.ports.input.rs.mapper.PersonajeControllerMapper;
import app.disney.ports.input.rs.request.PersonajeRequest;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;
import app.disney.ports.input.rs.response.PersonajeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.PERSONAJES_URI)
@RequiredArgsConstructor
public class PersonajeController {

    private final IPersonajeService personajeService;

    private final PersonajeControllerMapper mapper;

    @GetMapping()
    public ResponseEntity<List<PersonajeResponse>> getPersonajes(@RequestParam(required = false) String name,
                                                                 @RequestParam(required = false) Integer age,
                                                                 @RequestParam(required = false) Integer weight,
                                                                 @RequestParam(required = false) Long idMovie) {

        PersonajeRequestFilter request = new PersonajeRequestFilter(name, age, weight, idMovie);

        List<PersonajeResponse> list;

        {
            if (request.getName() != null ||
                    request.getAge() != null ||
                    request.getWeight() != null ||
                    request.getIdMovie() != null)
                list = mapper.personajeToListPersonajeResponse(personajeService.getAllPersonajesByFilter(request));
            else
                list = mapper.personajeToListPersonajeResponse(personajeService.getAllPersonajes());
        }
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonaje(@Valid @NotBlank @PathVariable Long id) {
        personajeService.deletePersonajeById(id);
    }

    @PostMapping("/savePersonaje")
    public ResponseEntity<Void> savePersonaje(@Valid @RequestBody PersonajeRequest request) {

        List<Movie> movies = personajeService.getMovies(request.getMovies());

        Personaje personaje = mapper.personajeRequestToPersonaje(request);
        personaje.setListMovie(movies);

        final long id = personajeService.savePersonaje(personaje);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/editCharacter/{id}")
    public ResponseEntity<PersonajeResponse> updatePersonaje(@Valid @NotNull @PathVariable Long id,
                                                             @Valid @RequestBody PersonajeRequest upPersonaje) {

        Personaje personaje = mapper.personajeRequestToPersonaje(upPersonaje);
        PersonajeResponse response = mapper.personajeToPersonajeResponse(personajeService.updatePersonaje(id, personaje));

        return ResponseEntity.ok().body(response);
    }

}
