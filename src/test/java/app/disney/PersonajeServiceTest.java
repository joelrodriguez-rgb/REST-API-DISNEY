package app.disney;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.controller.charactersController;
import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IGenderService;
import app.disney.service.IPersonajeService;
import app.disney.specification.MovieSpecification;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")

class PersonajeServiceTest {

	@Autowired
	private IPersonajeService personajeService;

	@Mock
	private IMovieRepository movieRepo;

	@Mock
	private IPersonajeRepository personajeRepo;

	@Mock
	private IMapper mapping;

	@Mock
	private IGenderService genderService;

	@Mock
	private PersonajeSpecification spec;



	private Personaje character1;
	private Movie movie1;
	private Gender gen1;
	private LocalDate date1 ;

//	private Personaje character1;
//	private Personaje character2;

	private ArrayList<Personaje> listPersonaje;

	private Personaje searchP;

	@BeforeEach
	void setUp() throws Exception {

		 date1 = LocalDate.of(2000, 2, 2);

		 gen1 = new Gender("dibujo");
//		Gender gen2 = new Gender("animado");
//		Gender gen3 = new Gender("terror");

		 movie1 = new Movie("PELICULA 01", date1, 5, gen1);
//		Movie movie2 = new Movie("PELICULA 02", date1, 5, gen1);

		character1 = new Personaje("PERSONAJE 01", 50, 50, movie1);
//		Personaje character2 = new Personaje("PERSONAJE 02", 50, 50, movie2);

		listPersonaje = new ArrayList<Personaje>();
		listPersonaje.add(character1);
	}
/*
	@Test
	void listFullPersonajesTest() {

		when(personajeRepo.findAll()).thenReturn(listPersonaje);
		assertNotNull(this.personajeService.getAllPersonaje());
	}
	
	@Test
	void savePersonajeTest() {

		when(personajeRepo.findByNameIgnoreCase("personaje 01")).thenReturn(character1);
		assertNotNull(personajeService.getByNameIgnoreCase("personaje 01"));
		

	}
*/


	@AfterEach
	void tearDown() throws Exception {
	}

}
