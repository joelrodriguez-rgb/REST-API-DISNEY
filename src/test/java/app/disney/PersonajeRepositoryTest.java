package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.specification.PersonajeSpecification;
import app.disney.util.IMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Transactional
class PersonajeRepositoryTest {

	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMovieRepository movieRepo;

	@Autowired
	private IGenderRepository genderRepo;

	@Autowired
	private IMapper mapping;

	@Autowired
	private PersonajeSpecification spec;

	@BeforeEach
	void seendDb() {

		LocalDate date1 = LocalDate.of(2003, 6, 20);
		Movie mov1 = new Movie("PELICULA 01", date1, 3, genderRepo.findByGenderName("dibujo"));
		movieRepo.save(mov1);

		LocalDate date2 = LocalDate.of(2008, 5, 4);
		Movie mov2 = new Movie("PELICULA 02", date2, 5, genderRepo.findByGenderName("dibujo"));
		movieRepo.save(mov2);

		LocalDate date3 = LocalDate.of(2015, 9, 15);
		Movie mov3 = new Movie("PELICULA 03", date3, 5, genderRepo.findByGenderName("animado"));
		movieRepo.save(mov3);

		List<Movie> listMovie = new ArrayList<>();
		listMovie.add(movieRepo.findByTitleIgnoreCase("pelicula 01"));
		listMovie.add(movieRepo.findByTitleIgnoreCase("pelicula 02"));

		Personaje personaje01 = new Personaje("personaje 01", 10, 1, listMovie);
		personajeRepo.save(personaje01);

		Personaje personaje02 = new Personaje("personaje 02", 20, 2, listMovie);
		personajeRepo.save(personaje02);

		Personaje personaje03 = new Personaje("personaje 03", 10, 3, movieRepo.findByTitleIgnoreCase("pelicula 03"));
		personajeRepo.save(personaje03);
	}

	/**
	 * @Query(value = "SELECT mov.title FROM personaje_mov pmov, movies mov " +
	 *              "WHERE pmov.movie_id = mov.id " + "AND pmov.personaje_id = ?1",
	 *              nativeQuery = true) List<String> findMovieByPersonajeId(Integer
	 *              personajeID);
	 */
	@Test
	void findMovieByPersonajeIdTest() {
		assertNotNull(personajeRepo.findMovieByPersonajeId(1));
		assertNotNull(personajeRepo.findMovieByPersonajeId(2));
		assertNotNull(personajeRepo.findMovieByPersonajeId(3));
	}

	@Test
	void findByNameIgnoreCaseTest() {

		assertEquals("personaje 01", personajeRepo.findByNameIgnoreCase("pErSoNAJE 01").getName());

	}

	@Test
	void findAllByNameSpecTest() {

		SearchPersonajeDTO search = new SearchPersonajeDTO("personaje 01", null, null, null);
		Personaje personaje = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);

		assertEquals("personaje 01", personajeRepo.findAll(personajeSpec).get(0).getName());

	}

	@Test
	void findAllByYearSpecTest() {

		SearchPersonajeDTO search = new SearchPersonajeDTO(null, 10, null, null);
		Personaje personaje = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);

		// personaje 01, personaje 03
		assertEquals("personaje 01", personajeRepo.findAll(personajeSpec).get(0).getName());
		assertEquals("personaje 03", personajeRepo.findAll(personajeSpec).get(1).getName());

	}

	@Test
	void findAllByWeightSpecTest() {

		SearchPersonajeDTO search = new SearchPersonajeDTO(null, null, 1, null);
		Personaje personaje = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);

		// personaje 01
		assertEquals("personaje 01", personajeRepo.findAll(personajeSpec).get(0).getName());

	}

	@Test
	void findAllByMovieSpecTest() {
		MovieDTO movie = new MovieDTO("pelicula 01");
		SearchPersonajeDTO search = new SearchPersonajeDTO(null, null, null, movie);
		Personaje personaje = mapping.mappingSearchPersonajeToEntity(search);
		Specification<Personaje> personajeSpec = spec.getAllBySpec(personaje);

		// personaje 01, pelicula 02
		assertEquals("personaje 01", personajeRepo.findAll(personajeSpec).get(0).getName());
		assertEquals("personaje 02", personajeRepo.findAll(personajeSpec).get(1).getName());

	}

	@AfterEach
	void deleteAllDb() {
		personajeRepo.deleteAll();
		movieRepo.deleteAll();
	}

}
