package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.disney.entitys.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {

	
	Movie findByTitleIgnoreCase(String title);
	
	
	@Query(value = "SELECT * FROM movies mov, gender gen "
			+ "WHERE mov.gender_id = gen.id "
			+ "AND gen.gender_name = ?1" , nativeQuery = true)
	List<Movie> findByGender(String gender);
	
}
