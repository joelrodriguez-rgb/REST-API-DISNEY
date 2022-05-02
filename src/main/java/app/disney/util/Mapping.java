package app.disney.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import app.disney.domain.model.Gender;
import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;

public class Mapping implements IMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PersonajeDTO mappingPersonajeToDTO(Personaje personaje) {
		return modelMapper.map(personaje, PersonajeDTO.class);
	}

	@Override
	public Personaje mappingPersonajeDTOToEntity(PersonajeDTO personaje) {
		return modelMapper.map(personaje, Personaje.class);
	}

	@Override
	public Personaje mappingSearchPersonajeToEntity(SearchPersonajeDTO search) {
		return modelMapper.map(search, Personaje.class);
	}

	@Override
	public List<?> mappingListPersonajesToDTO(List<?> list) {

		List<PersonajeDTO> listPersonajeDTO = list.stream()
				.map(personajes -> modelMapper.map(personajes, PersonajeDTO.class)).collect(Collectors.toList());
		return listPersonajeDTO;
	}

	@Override
	public MovieDTO mappingMovieToDTO(Movie movie) {
		return modelMapper.map(movie, MovieDTO.class);
	}

	@Override
	public Movie mappingMovieDTOToEntity(MovieDTO movie) {
		return modelMapper.map(movie, Movie.class);
	}

	@Override
	public Movie mappingSearchMovieToEntity(SearchMovieDTO search) {
		return modelMapper.map(search, Movie.class);

	}

	@Override
	public List<?> mappingListMovie(List<?> list) {

		if (list.get(0).getClass().equals(MovieDTO.class)) {

			List<Movie> listMovieModel = list.stream().map(movies -> modelMapper.map(movies, Movie.class))
					.collect(Collectors.toList());
			return listMovieModel;

		} else {

			List<MovieDTO> listMovieDTO = list.stream().map(movies -> modelMapper.map(movies, MovieDTO.class))
					.collect(Collectors.toList());
			return listMovieDTO;

		}
	}

	@Override
	public List<?> mappingListGender(List<?> list) {
		if (list.get(0).getClass().equals(GenderDTO.class)) {

			List<Gender> listGenderModel = list.stream().map(genders -> modelMapper.map(genders, Gender.class))
					.collect(Collectors.toList());
			return listGenderModel;

		} else {

			List<GenderDTO> listGenderDTO = list.stream().map(genders -> modelMapper.map(genders, GenderDTO.class))
					.collect(Collectors.toList());
			return listGenderDTO;

		}
	}

}
