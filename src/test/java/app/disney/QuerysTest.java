package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import app.disney.entitys.Gender;
import app.disney.entitys.MovieOrSerie;
import app.disney.entitys.Personaje;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMosRepository;
import app.disney.repository.IPersonajeRepository;

@SpringBootTest
@RunWith(value = SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Transactional
public class QuerysTest {

	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMosRepository mosRepo;

	@Autowired
	private IGenderRepository genderRepo;

	@Autowired
	private EntityManager entityManager;


	// carga de base de pruebas
	@BeforeEach
	void seendDb() {
		Gender gen1 = new Gender("dibujo");
		Gender gen2 = new Gender("animado");
		Gender gen3 = new Gender("terror");

		genderRepo.save(gen1);
		genderRepo.save(gen2);
		genderRepo.save(gen3);

		LocalDate date1 = LocalDate.of(2003, 6, 20);
		MovieOrSerie mov1 = new MovieOrSerie();
		mov1.setTitle("la Bella y la Bestia");
		mov1.setCreationDate(date1);
		mov1.setQualification(3);
		mov1.setGender(genderRepo.findByGenderName("dibujo"));
		entityManager.merge(mov1);

		LocalDate date2 = LocalDate.of(2008, 5, 4);
		MovieOrSerie mov2 = new MovieOrSerie();
		mov2.setTitle("la Bella y la Bestia 2");
		mov2.setCreationDate(date2);
		mov2.setQualification(5);
		mov2.setGender(genderRepo.findByGenderName("dibujo"));
		entityManager.merge(mov2);

		List<MovieOrSerie> listMos = mosRepo.findAll();

		Personaje bestia = new Personaje("Bestia", 35, 120.0, listMos);
		entityManager.merge(bestia);

		Personaje bella = new Personaje("Bella", 25, 60.0, listMos);
		entityManager.merge(bella);


	}

	/** QUERYS DE PERSONAJES */

	@Test // List<String> findMovieByPersonajeId(Integer personajeID);
	void testFindFirstByPersonajeNameIgnoreCase() {

		assertEquals("Bestia", personajeRepo.findFirstByPersonajeNameIgnoreCase("BesTiA").getpersonajeName());
		assertEquals("Bella", personajeRepo.findFirstByPersonajeNameIgnoreCase("BELLA").getpersonajeName());
	}

	@Test // Personaje findFirstByPersonajeNameIgnoreCase(String name);
	void testFindMovieByPersonajeId() {

		Integer idBestia = personajeRepo.findFirstByPersonajeNameIgnoreCase("bestia").getId();
		Integer idBella = personajeRepo.findFirstByPersonajeNameIgnoreCase("bella").getId();

		List<String> listMovieByBestia = personajeRepo.findMovieByPersonajeId(idBestia);
		List<String> listMovieByBella = personajeRepo.findMovieByPersonajeId(idBella);

		assertEquals(false, listMovieByBestia.isEmpty());
		assertEquals(false, listMovieByBella.isEmpty());
	}

	@AfterEach
	void deleteAllDb() {
		personajeRepo.deleteAll();
		mosRepo.deleteAll();
		genderRepo.deleteAll();
	}

}
