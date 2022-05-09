package app.disney.domain.usercase;

import app.disney.domain.model.Personaje;

import java.util.List;

public interface IPersonajeService {

    Long savePersonaje(Personaje personaje);

    Personaje updatePersonaje(Long id, Personaje upPersonaje);

    void deletePersonajeById(Long id);

    List<Personaje> getPersonajes(Personaje request);

    void validateName(String name);


}
