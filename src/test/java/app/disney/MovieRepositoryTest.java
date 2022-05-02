package app.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import app.disney.domain.model.Gender;
import app.disney.domain.model.Movie;
import app.disney.domain.repository.IMovieRepository;
import app.disney.ports.input.rs.api.specification.MovieSpecification;
import app.disney.util.IMapper;


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
