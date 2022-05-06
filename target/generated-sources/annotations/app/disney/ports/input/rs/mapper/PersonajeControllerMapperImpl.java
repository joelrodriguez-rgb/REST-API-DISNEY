package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequest;
import app.disney.ports.input.rs.response.PersonajeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class PersonajeControllerMapperImpl implements PersonajeControllerMapper {

    @Override
    public Personaje personajeRequestToPersonaje(PersonajeRequest request) {
        if ( request == null ) {
            return null;
        }

        Personaje personaje = new Personaje();

        personaje.setName( request.getName() );
        personaje.setImgPersonaje( request.getImgPersonaje() );
        personaje.setYear( request.getYear() );
        personaje.setWeight( request.getWeight() );

        return personaje;
    }

    @Override
    public Personaje personajeResponseToPersonaje(PersonajeResponse personajeResponse) {
        if ( personajeResponse == null ) {
            return null;
        }

        Personaje personaje = new Personaje();

        personaje.setId( personajeResponse.getId() );
        personaje.setName( personajeResponse.getName() );
        personaje.setImgPersonaje( personajeResponse.getImgPersonaje() );
        personaje.setYear( personajeResponse.getYear() );
        personaje.setWeight( personajeResponse.getWeight() );

        return personaje;
    }

    @Override
    public PersonajeResponse personajeToPersonajeResponse(Personaje personaje) {
        if ( personaje == null ) {
            return null;
        }

        PersonajeResponse personajeResponse = new PersonajeResponse();

        personajeResponse.setId( personaje.getId() );
        personajeResponse.setName( personaje.getName() );
        personajeResponse.setImgPersonaje( personaje.getImgPersonaje() );
        personajeResponse.setYear( personaje.getYear() );
        personajeResponse.setWeight( personaje.getWeight() );

        return personajeResponse;
    }

    @Override
    public List<PersonajeResponse> personajeToListPersonajeResponse(List<Personaje> listPersonajes) {
        if ( listPersonajes == null ) {
            return null;
        }

        List<PersonajeResponse> list = new ArrayList<PersonajeResponse>( listPersonajes.size() );
        for ( Personaje personaje : listPersonajes ) {
            list.add( personajeToPersonajeResponse( personaje ) );
        }

        return list;
    }

    @Override
    public void updatePersonajeFromPersonajeResponse(PersonajeResponse personajeResponse, Personaje personaje) {
        if ( personajeResponse == null ) {
            return;
        }

        if ( personajeResponse.getId() != null ) {
            personaje.setId( personajeResponse.getId() );
        }
        if ( personajeResponse.getName() != null ) {
            personaje.setName( personajeResponse.getName() );
        }
        if ( personajeResponse.getImgPersonaje() != null ) {
            personaje.setImgPersonaje( personajeResponse.getImgPersonaje() );
        }
        if ( personajeResponse.getYear() != null ) {
            personaje.setYear( personajeResponse.getYear() );
        }
        if ( personajeResponse.getWeight() != null ) {
            personaje.setWeight( personajeResponse.getWeight() );
        }
    }
}
