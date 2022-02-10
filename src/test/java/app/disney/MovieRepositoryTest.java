package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import app.disney.DTO.GenderDTO;
import app.disney.DTO.SearchMovieDTO;
import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import app.disney.repository.IMovieRepository;
import app.disney.specification.MovieSpecification;
import app.disney.util.IMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
class MovieRepositoryTest {


	@Mock
	private IMovieRepository movieRepo;
	
	@Autowired
	private IMapper mapping;

	@Autowired
	private MovieSpecification spec;
	
	
	@Test
	void findByTitleIgnoreCaseTest() {
		//GIVEN
		Gender gen = new Gender("dibujo");
		LocalDate date = LocalDate.of(2003, 6, 20);
		Movie mov = new Movie("PELICULA 01", date, 3, gen);
		movieRepo.save(mov);
		
		//WHEN
		when(movieRepo.findByTitleIgnoreCase("pElIcULA 01")).thenReturn(mov);
		
		//THEN
		assertEquals(mov, movieRepo.findByTitleIgnoreCase("pElIcULA 01"));
	}

	/**
	 * @Query(value = "SELECT * FROM movies mov, gender gen " 
	 *              + "WHERE mov.gender_id = gen.id " 
	 *              + "AND gen.gender_name = ?1" , nativeQuery = true)
	 */
	@Test
	void findByGenderTest() {
		
		//GIVEN
		Gender gen = new Gender("dibujo");
		LocalDate date = LocalDate.of(2003, 6, 20);
		Movie mov1 = new Movie("PELICULA 01", date, 3, gen);
		Movie mov2 = new Movie("PELICULA 02", date, 3, gen);
		List<Movie> list = new ArrayList<Movie>();
		list.add(mov1);
		list.add(mov2);
		
		
		//WHEN
		when(movieRepo.findByGender("dibujo")).thenReturn(list);
		
		//THEN
		assertNotNull( movieRepo.findByGender("dibujo"));
		assertEquals(mov1, movieRepo.findByGender("dibujo").get(0));
		assertEquals(mov2, movieRepo.findByGender("dibujo").get(1));

	}
	


	@Test
	void findAllByTitleSpecTest() {
        //GIVEN
		
		//Obtengo el objeto a especificado
		SearchMovieDTO search = new SearchMovieDTO("pelicula 01",null);
		Movie movieSearch = mapping.mappingSearchMovieToEntity(search);
		Specification<Movie> movieSpec = spec.getAllBySpec(movieSearch);
		
		
		//Preparo el Objeto que tengo que buscar
		Gender gen = new Gender("dibujo");
		LocalDate date = LocalDate.of(2003, 6, 20);
		Movie movie = new Movie("PELICULA 01", date, 3, gen);
		
		List<Movie> list = new ArrayList();
		list.add(movie);
		
		//WHEN
		when(movieRepo.findAll(movieSpec)).thenReturn(list);
		
		//THEN
		assertEquals(movie.getTitle(), movieRepo.findAll(movieSpec).get(0).getTitle());

	}

	@Test
	void findAllByGenderSpecTest() {
		
	    //GIVEN
		
		//Obtengo el objeto a especificado
		GenderDTO gender = new GenderDTO("dibujo");
		SearchMovieDTO search = new SearchMovieDTO(null, gender);
		Movie movieSearch = mapping.mappingSearchMovieToEntity(search);
		Specification<Movie> movieSpec = spec.getAllBySpec(movieSearch);
		
		//Preparo el Objeto que tengo que buscar
		Gender gen = new Gender("dibujo");
		LocalDate date = LocalDate.of(2003, 6, 20);
		Movie movie = new Movie("PELICULA 01", date, 3, gen);
		
		List<Movie> list = new ArrayList<Movie>();
		list.add(movie);
		
		//WHEN
		when(movieRepo.findAll(movieSpec)).thenReturn(list);
		
		//THEN
		assertEquals(movie.getTitle(), movieRepo.findAll(movieSpec).get(0).getTitle());
	}

}
