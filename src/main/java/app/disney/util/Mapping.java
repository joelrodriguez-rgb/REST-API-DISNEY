package app.disney.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

public class Mapping implements IMapper {

	@Autowired
	private ModelMapper modelMapper;

	public List<?> mappingListPersonajes(List<?> list) {

		List<PersonajeDTO> listPersonajeDTO = list.stream()
				.map(personajes -> modelMapper.map(personajes, PersonajeDTO.class)).collect(Collectors.toList());
		return listPersonajeDTO;
	}

	public Object mappingPersonaje(Object personaje) {

		if (personaje.getClass().equals(Personaje.class))
			return modelMapper.map(personaje, PersonajeDTO.class);
		else
			return modelMapper.map(personaje, Personaje.class);

	}

	@Override
	public List<?> mappingListMovie(List<?> list) {

		if (list.get(0).getClass().equals(MovieDTO.class)) {

			List<?> listMovieModel = list.stream().map(movies -> modelMapper.map(movies, Movie.class))
					.collect(Collectors.toList());
			return listMovieModel;

		} else {

			List<MovieDTO> listMovieDTO = list.stream().map(movies -> modelMapper.map(movies, MovieDTO.class))
					.collect(Collectors.toList());
			return listMovieDTO;

		}
	}

}
