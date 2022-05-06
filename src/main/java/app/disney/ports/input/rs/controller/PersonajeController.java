package app.disney.ports.input.rs.controller;

import app.disney.domain.model.Personaje;
import app.disney.domain.usercase.IPersonajeService;
import app.disney.ports.input.rs.mapper.PersonajeControllerMapper;
import app.disney.ports.input.rs.request.PersonajeRequest;
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

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class PersonajeController {

    private final IPersonajeService personajeService;

    private final PersonajeControllerMapper mapper;

    @GetMapping()
    public ResponseEntity<List<PersonajeResponse>> getPersonajes(@Valid @RequestBody PersonajeRequest request) {

        Personaje personaje = mapper.personajeRequestToPersonaje(request);

        List<PersonajeResponse> list = mapper.personajeToListPersonajeResponse(personajeService.getPersonajes(personaje));

        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonaje(@Valid @NotBlank @PathVariable Integer id) {
        personajeService.deletePersonajeById(id);
    }

    @PostMapping("/saveCharacter")
    public ResponseEntity<Void> saveStudent(@Valid @RequestBody PersonajeRequest request) {

        Personaje personaje = mapper.personajeRequestToPersonaje(request);
        final int id = personajeService.savePersonaje(personaje);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/editCharacter/{id}")
    public ResponseEntity<PersonajeResponse> updatePersonaje(@Valid @NotNull @PathVariable Integer id,
                                                             @Valid @RequestBody PersonajeRequest upPersonaje) {

        Personaje personaje = mapper.personajeRequestToPersonaje(upPersonaje);
        PersonajeResponse response = mapper.personajeToPersonajeResponse(personajeService.updatePersonaje(id, personaje));

        return ResponseEntity.ok().body(response);
    }

}
