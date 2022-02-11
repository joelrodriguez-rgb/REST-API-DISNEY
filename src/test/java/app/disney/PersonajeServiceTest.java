package app.disney;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.PersonajeDTO;
import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IMovieService;
import app.disney.service.IPersonajeService;
import app.disney.service.implement.PersonajeServiceImplement;
import app.disney.specification.PersonajeSpecification;
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
