package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequest;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;
import app.disney.ports.input.rs.response.PersonajeResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface PersonajeControllerMapper extends CommonMapper {

    //@Mapping(source = "movies", target = "listMovie.id")
    Personaje personajeRequestToPersonaje(PersonajeRequest request);

    PersonajeResponse personajeToPersonajeResponse(Personaje personaje);

    List<PersonajeResponse> personajeToListPersonajeResponse (List<Personaje> listPersonajes);

}
