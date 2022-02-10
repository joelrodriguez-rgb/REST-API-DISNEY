package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import app.disney.DTO.MovieDTO;
import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Personaje;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
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

}
