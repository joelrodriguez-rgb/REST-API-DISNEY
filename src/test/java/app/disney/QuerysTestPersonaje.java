package app.disney;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Transactional
class QuerysTestPersonaje {

//	@Autowired
//	private IPersonajeRepository personajeRepo;
//
//	@Autowired
//	private IMovieRepository movieRepo;
//
//	@Autowired
//	private IGenderRepository genderRepo;
//
//
//	// carga de base de pruebas
//	@BeforeEach
//	void seendDb() {
//		Gender gen1 = new Gender("dibujo");
//		Gender gen2 = new Gender("animado");
//		Gender gen3 = new Gender("terror");
//
//		genderRepo.save(gen1);
//		genderRepo.save(gen2);
//		genderRepo.save(gen3);
//
//		LocalDate date1 = LocalDate.of(2003, 6, 20);
//		Movie mov1 = new Movie("la Bella y la Bestia", date1, 3, genderRepo.findByGenderName("dibujo"));
//		movieRepo.save(mov1);
//
//		LocalDate date2 = LocalDate.of(2008, 5, 4);
//		Movie mov2 = new Movie("la Bella y la Bestia 2", date2, 5, genderRepo.findByGenderName("dibujo"));
//		movieRepo.save(mov2);
//
//		LocalDate date3 = LocalDate.of(2015, 9, 15);
//		Movie mov3 = new Movie("la lampara de Aladin", date3, 5, genderRepo.findByGenderName("animado"));
//		movieRepo.save(mov3);
//
//		List<Movie> listMos = new ArrayList<>();
//		listMos.add(movieRepo.findByTitleIgnoreCase("la bella y la bestia"));
//		listMos.add(movieRepo.findByTitleIgnoreCase("la bella y la bestia 2"));
//
//		Personaje bestia = new Personaje("Bestia", 35, 120, listMos);
//		personajeRepo.save(bestia);
//
//		Personaje bella = new Personaje("Bella", 25, 60, listMos);
//		personajeRepo.save(bella);
//
//		Personaje aladin = new Personaje("Aladin", 35, 60,  movieRepo.findByTitleIgnoreCase("la lampara de aladin"));
//		personajeRepo.save(aladin);
//	}
//
//
//	@Test // List<String> findMovieByPersonajeId(Integer personajeID);
//	void testFindFirstByPersonajeNameIgnoreCase() {
//
//		assertEquals("Bestia", personajeRepo.findByNameIgnoreCase("BesTiA").getName());
//		assertEquals("Bella", personajeRepo.findByNameIgnoreCase("BELLA").getName());
//	}
//
//	@Test // Personaje findFirstByPersonajeNameIgnoreCase(String name);
//	void testFindMovieByPersonajeId() {
//
//		Integer idBestia = personajeRepo.findByNameIgnoreCase("bestia").getId();
//		Integer idBella = personajeRepo.findByNameIgnoreCase("bella").getId();
//
//		List<String> listMovieByBestia = personajeRepo.findMovieByPersonajeId(idBestia);
//		List<String> listMovieByBella = personajeRepo.findMovieByPersonajeId(idBella);
//
//		assertEquals(false, listMovieByBestia.isEmpty());
//		assertEquals(false, listMovieByBella.isEmpty());
//
//	}
//
//	@Test
//	void testFindByYear() {
//		List<Personaje> listPersonajeByYear = personajeRepo.findByYear(35);
//		assertEquals(false, listPersonajeByYear.isEmpty());
//	}
//
//	@Test
//	void testFindByMovie() {
//		List<Personaje> listPersonajes = personajeRepo.findByMovie("la bella y la bestia");
//		assertEquals(false, listPersonajes.isEmpty());
//	}
//
//	@AfterEach
//	void deleteAllDb() {
//		personajeRepo.deleteAll();
//		movieRepo.deleteAll();
//		genderRepo.deleteAll();
//	}

}
