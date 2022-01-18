package app.disney.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import app.disney.DTO.PersonajeDTO;
import app.disney.entitys.Personaje;

public class Mapping<T> implements IMapper<T> {

	@Autowired
	private ModelMapper modelMapper;

	public List<T> mappingListPersonajes(List<T> list) {
		
		String nameClass = list.get(0).getClass().getSimpleName();

		if (nameClass.matches("[a-z][DTO]")) {
			List<Personaje> listPersonaje = list.stream()
					.map(personajes -> modelMapper.map(personajes, Personaje.class)).collect(Collectors.toList());

			return (List<T>) listPersonaje;

		} else {

			List<PersonajeDTO> listPersonajeDTO = list.stream()
					.map(personajes -> modelMapper.map(personajes, PersonajeDTO.class)).collect(Collectors.toList());
			return (List<T>) listPersonajeDTO;
		}

	}

}
