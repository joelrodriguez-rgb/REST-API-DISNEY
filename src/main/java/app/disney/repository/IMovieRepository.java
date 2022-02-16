package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import app.disney.entitys.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer>,
                                          JpaSpecificationExecutor<Movie>  {

	List<Movie> findAll(Specification<Movie> spec);
	
	Movie findByTitleIgnoreCase(String title);
	
	List<Movie> findAllOrderByCreationDateAsc();
	
	List<Movie> findAllOrderByCreationDateDesc();
	
}
