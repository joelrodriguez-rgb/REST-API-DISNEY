package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.service.IGenderService;
import app.disney.service.IPersonajeService;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@SpringBootTest
class PersonajeServiceTest {

	@Autowired
	private IPersonajeService personajeService;

	@MockBean
	private IMovieRepository movieRepo;

	@MockBean
	private IPersonajeRepository personajeRepo;

	@MockBean
	private IMapper mapping;

	@MockBean
	private IGenderService genderService;

	@MockBean
	private PersonajeSpecification spec;


//	@BeforeEach
//	void setUp() throws Exception {
//		
//		//GIVEN
//
//		LocalDate date1 = LocalDate.of(2000, 2, 2);
//
//		Gender gen1 = new Gender("dibujo");
//		Gender gen2 = new Gender("animado");
//		Gender gen3 = new Gender("terror");
//
//		Movie movie1 = new Movie("PELICULA 01", date1, 5, gen1);
//		Movie movie2 = new Movie("PELICULA 02", date1, 5, gen2);
//
//		Personaje character1 = new Personaje("PERSONAJE 01", 50, 50, movie1);
//		Personaje character2 = new Personaje("PERSONAJE 02", 50, 50, movie2);
//
//		listPersonaje = new ArrayList<Personaje>();
//		listPersonaje.add(character1);
//		listPersonaje.add(character2);
//	}

	@Test
	void getAllPersonajeTest() {
		
		
		//WHEN

		List<?> list = personajeRepo.findAll();
		
		//THEN
		
		assertEquals(list,personajeService.getAllPersonaje());
	}
	




	@AfterEach
	void tearDown() throws Exception {
	}

}
