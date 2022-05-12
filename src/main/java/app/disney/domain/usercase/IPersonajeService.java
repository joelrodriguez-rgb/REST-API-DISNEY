package app.disney.domain.usercase;

import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;

import java.util.List;

public interface IPersonajeService {

    Long savePersonaje(Personaje personaje);

    Personaje updatePersonaje(Long id, Personaje upPersonaje);

    void deletePersonajeById(Long id);

    List<Personaje> getAllPersonajes();

    List<Personaje> getAllPersonajesByFilter(PersonajeRequestFilter request);

    void validateName(String name);


}
