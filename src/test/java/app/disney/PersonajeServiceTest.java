package app.disney;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import app.disney.domain.model.Gender;
import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.domain.repository.IGenderRepository;
import app.disney.domain.repository.IMovieRepository;
import app.disney.domain.repository.IPersonajeRepository;
import app.disney.domain.usercase.IMovieService;
import app.disney.domain.usercase.IPersonajeService;
import app.disney.domain.usercase.impl.PersonajeServiceImplement;
import app.disney.ports.input.rs.api.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class PersonajeServiceTest {

	@InjectMocks
	private IPersonajeService personajeService = new PersonajeServiceImplement();

	@Autowired
	private IMovieRepository movieRepo;

	@Autowired
	private IGenderRepository genderRepo;
	
	@MockBean
	private IPersonajeRepository personajeRepo;
	
	
	@MockBean
	private IMovieService movieService;

	@MockBean
	private IMapper mapping;

	@MockBean
	private PersonajeSpecification spec;
	
	@BeforeEach
	void setUp() throws Exception {
		
		//GIVEN

		LocalDate date1 = LocalDate.of(2000, 2, 2);

		Gender gen1 = new Gender("dibujo");
		Gender gen2 = new Gender("animado");
		Gender gen3 = new Gender("terror");
		
		genderRepo.save(gen1);
		genderRepo.save(gen2);
		genderRepo.save(gen3);

		Movie movie1 = new Movie("PELICULA 01", date1, 5, gen1);
		movieRepo.save(movie1);
		
		Movie movie2 = new Movie("PELICULA 02", date1, 5, gen2);
		movieRepo.save(movie2);

		Personaje character1 = new Personaje("PERSONAJE 01", 50, 50, movie1);
		personajeRepo.save(character1);
		Personaje character2 = new Personaje("PERSONAJE 02", 50, 50, movie2);
		personajeRepo.save(character2);
		
	}

	@Test
	void nameNewPersonajeExistingInDBTest() {
		//GIVEN
	
		String name = "personaje 01";
		
		//WHEN		
		//THEN
		
		assertNotNull(personajeRepo.findByNameIgnoreCase("personaje 03"));
		
		
		

	}
	




	@AfterEach
	void tearDown() {
		personajeRepo.deleteAll();
		movieRepo.deleteAll();
		genderRepo.deleteAll();
	}

}
