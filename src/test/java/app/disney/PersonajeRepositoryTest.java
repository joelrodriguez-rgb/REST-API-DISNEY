package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.repository.IPersonajeRepository;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonajeRepositoryTest {

	@MockBean
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMapper mapping;

	@Autowired
	private PersonajeSpecification spec;



	@Test
	void findByNameIgnoreCaseTest() {

		//GIVEN
		LocalDate date = LocalDate.of(2003, 6, 20);
		Movie movie = new Movie("pelicula", date, 5);
		Personaje personaje = new Personaje("personaje 01", 50, 50, movie);
		
		//WHEN
		when(personajeRepo.findByNameIgnoreCase("pErSoNAJE 01")).thenReturn(personaje);
		
		//THEN
		assertEquals(personaje, personajeRepo.findByNameIgnoreCase("pErSoNAJE 01"));

	}

	@Test
	void findAllByNameSpecTest() {
        //GIVEN
		
		//Obtengo el objeto a especificado
		SearchPersonajeDTO search = new SearchPersonajeDTO("personaje 01", null, null, null);
		Personaje personajeSearch = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personajeSearch);
		
		//Preparo el Objeto que tengo que buscar
		Personaje personaje = new Personaje("personaje 01",50,50,new Movie());
		List<Personaje> list = new ArrayList<Personaje>();
		list.add(personaje);
		
		//WHEN
		when(personajeRepo.findAll(personajeSpec)).thenReturn(list);
		
		
	    //THEN
		assertEquals(personaje.getName(), personajeRepo.findAll(personajeSpec).get(0).getName());

	}

	@Test
	void findAllByYearSpecTest() {
        //GIVEN
		
		//Obtengo el objeto a especificado
		SearchPersonajeDTO search = new SearchPersonajeDTO(null, 50, null, null);
		Personaje personaje = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);
		
		//Preparo el Objeto que tengo que buscar
		Personaje personaje1 = new Personaje("personaje 01",50,50,new Movie());
		Personaje personaje2 = new Personaje("personaje 02",50,50,new Movie());
		List<Personaje> list = new ArrayList<Personaje>();
		list.add(personaje1);
		list.add(personaje2);
		
		
		//WHEN
		when(personajeRepo.findAll(personajeSpec)).thenReturn(list);
		

		// personaje 01, personaje 03
		assertEquals(personaje1.getName(), personajeRepo.findAll(personajeSpec).get(0).getName());
		assertEquals(personaje2.getName(), personajeRepo.findAll(personajeSpec).get(1).getName());

	}

	@Test
	void findAllByWeightSpecTest() {
        //GIVEN
		
		//Obtengo el objeto a especificado
		SearchPersonajeDTO search = new SearchPersonajeDTO(null, null, 50, null);
		Personaje personajeSearch = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personajeSearch);
		
		//Preparo el Objeto que tengo que buscar
		Personaje personaje = new Personaje("personaje 01",50,50,new Movie());
		List<Personaje> list = new ArrayList<Personaje>();
		list.add(personaje);
		
		
		//WHEN
		when(personajeRepo.findAll(personajeSpec)).thenReturn(list);

		// personaje 01
		assertEquals(personaje.getName(), personajeRepo.findAll(personajeSpec).get(0).getName());

	}

	@Test
	void findAllByMovieSpecTest() {
		MovieDTO movie = new MovieDTO("pelicula 01");
		SearchPersonajeDTO search = new SearchPersonajeDTO(null, null, null, movie);
		Personaje personajeSearch = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personajeSearch);
		
		
		//Preparo el Objeto que tengo que buscar
		Personaje personaje = new Personaje("personaje 01",50,50,mapping.mappingMovieDTOToEntity(movie));
		List<Personaje> list = new ArrayList<Personaje>();
		list.add(personaje);
		
		
		//WHEN
		when(personajeRepo.findAll(personajeSpec)).thenReturn(list);

		// personaje 01
		assertEquals(personaje.getName(), personajeRepo.findAll(personajeSpec).get(0).getName());

	}

}
