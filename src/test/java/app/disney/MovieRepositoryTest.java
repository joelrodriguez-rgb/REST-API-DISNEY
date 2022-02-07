package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import app.disney.DTO.GenderDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.specification.MovieSpecification;
import app.disney.util.IMapper;

@SpringBootTest
//Base de datos de pruebas
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Transactional
class MovieRepositoryTest {

	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMovieRepository movieRepo;

	@Autowired
	private IGenderRepository genderRepo;

	@Autowired
	private IMapper mapping;

	@Autowired
	private MovieSpecification spec;

	@BeforeEach
	void seendDb() {
		Gender gen1 = new Gender("dibujo");
		Gender gen2 = new Gender("animado");
		Gender gen3 = new Gender("terror");

		genderRepo.save(gen1);
		genderRepo.save(gen2);
		genderRepo.save(gen3);

		LocalDate date1 = LocalDate.of(2003, 6, 20);
		Movie mov1 = new Movie("PELICULA 01", date1, 3, genderRepo.findByGenderName("dibujo"));
		movieRepo.save(mov1);

		LocalDate date2 = LocalDate.of(2008, 5, 4);
		Movie mov2 = new Movie("PELICULA 02", date2, 5, genderRepo.findByGenderName("dibujo"));
		movieRepo.save(mov2);

		LocalDate date3 = LocalDate.of(2015, 9, 15);
		Movie mov3 = new Movie("PELICULA 03", date3, 5, genderRepo.findByGenderName("animado"));
		movieRepo.save(mov3);

	}

	@Test
	void findByTitleIgnoreCaseTest() {

		assertEquals("PELICULA 01", movieRepo.findByTitleIgnoreCase("pElIcULA 01").getTitle());
	}

	/**
	 * @Query(value = "SELECT * FROM movies mov, gender gen " 
	 *              + "WHERE mov.gender_id = gen.id " 
	 *              + "AND gen.gender_name = ?1" , nativeQuery = true)
	 */
	@Test
	void findByGenderTest() {

		assertEquals(false, movieRepo.findByGender("dibujo").isEmpty());
		assertEquals("PELICULA 01", movieRepo.findByGender("dibujo").get(0).getTitle());
		assertEquals("PELICULA 03", movieRepo.findByGender("animado").get(0).getTitle());

	}

	@Test
	void findAllByTitleSpecTest() {

		SearchMovieDTO search = new SearchMovieDTO("pelicula 01",null);
		Movie movie = mapping.mappingSearchMovieToEntity(search);
		Specification<Movie> movieSpec = spec.getAllBySpec(movie);
		
		assertEquals("PELICULA 01", movieRepo.findAll(movieSpec).get(0).getTitle());

	}

	@Test
	void findAllByGenderSpecTest() {

		GenderDTO gender = new GenderDTO("dibujo");
		SearchMovieDTO search = new SearchMovieDTO(null, gender);
		Movie movie = mapping.mappingSearchMovieToEntity(search);
		Specification<Movie> movieSpec = spec.getAllBySpec(movie);
		
		assertEquals("PELICULA 01", movieRepo.findAll(movieSpec).get(0).getTitle());
		assertEquals("PELICULA 02", movieRepo.findAll(movieSpec).get(1).getTitle());
	}

	@AfterEach
	void deleteAllDb() {
		personajeRepo.deleteAll();
		movieRepo.deleteAll();
		genderRepo.deleteAll();
	}

}
