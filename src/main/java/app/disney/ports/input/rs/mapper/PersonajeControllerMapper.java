package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequest;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;
import app.disney.ports.input.rs.response.PersonajeResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface PersonajeControllerMapper extends CommonMapper {

    Personaje personajeRequestToPersonaje(PersonajeRequest request);

    PersonajeResponse personajeToPersonajeResponse(Personaje personaje);

    List<PersonajeResponse> personajeToListPersonajeResponse (List<Personaje> listPersonajes);

    Personaje personajeRequestFilterToPersonaje (PersonajeRequestFilter requestFilter);
}
