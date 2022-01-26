package app.disney.util;

import java.beans.JavaBean;
import java.util.List;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

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
