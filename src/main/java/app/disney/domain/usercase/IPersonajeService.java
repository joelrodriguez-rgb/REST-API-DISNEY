package app.disney.domain.usercase;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;

import java.util.List;
import java.util.Optional;

public interface IPersonajeService {

    Long savePersonaje(Personaje personaje);

    Personaje updatePersonaje(Long id, Personaje upPersonaje);

    void deletePersonajeById(Long id);

    List<Personaje> getAllPersonajes();

    List<Personaje> getAllPersonajesByFilter(PersonajeRequestFilter request);

    void validateName(String name);

    List <Movie> getMovies(List<Long> movies);


}
