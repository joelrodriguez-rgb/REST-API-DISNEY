package app.disney.util;

import java.beans.JavaBean;
import java.util.List;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;

@JavaBean
public interface IMapper {

	public PersonajeDTO mappingPersonajeToDTO(Personaje personaje);

	public Personaje mappingPersonajeDTOToEntity(PersonajeDTO personaje);
	
	public Personaje mappingSearchPersonajeToEntity(SearchPersonajeDTO search);
	
	
	
	public MovieDTO mappingMovieToDTO(Movie movie);

	public Movie mappingMovieDTOToEntity(MovieDTO movie);
	
	public Movie mappingSearchMovieToEntity(SearchMovieDTO search);
	
	
	
	
	

	public List<?> mappingListPersonajesToDTO(List<?> list);

	public List<?> mappingListMovie(List<?> list);
	
	public List<?> mappingListGender(List<?> list);

}
