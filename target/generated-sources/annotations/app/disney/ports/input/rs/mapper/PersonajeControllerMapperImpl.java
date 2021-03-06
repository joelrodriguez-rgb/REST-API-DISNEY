package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.ports.input.rs.request.PersonajeRequest;
import app.disney.ports.input.rs.response.PersonajeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
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
        personaje.setAge( request.getAge() );
        personaje.setWeight( request.getWeight() );

        return personaje;
    }

    @Override
    public PersonajeResponse personajeToPersonajeResponse(Personaje personaje) {
        if ( personaje == null ) {
            return null;
        }

        PersonajeResponse personajeResponse = new PersonajeResponse();

        personajeResponse.setName( personaje.getName() );
        personajeResponse.setImgPersonaje( personaje.getImgPersonaje() );

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
    public Movie movieIdToMovie(Long id) {
        if ( id == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( id );

        return movie;
    }
}
