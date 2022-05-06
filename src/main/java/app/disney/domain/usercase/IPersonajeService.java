package app.disney.domain.usercase;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;

import java.util.List;

public interface IPersonajeService {

    Integer savePersonaje(Personaje personaje);

    Personaje updatePersonaje(Integer id, Personaje upPersonaje);

    void deletePersonajeById(Integer id);

    List<Personaje> getPersonajes(Personaje request);

    void validateName(String name);


}
