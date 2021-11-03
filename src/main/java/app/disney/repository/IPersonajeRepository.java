package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.disney.DTO.SearchPersonajeDTO;
import app.disney.entitys.Personaje;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Integer>,JpaSpecificationExecutor<Personaje> {
	
	
	List<Personaje> findAll(Specification<Personaje> spec);
	
	@Query(value = "SELECT mov.title FROM personaje_mov pmov, movies mov " 
	             + "WHERE pmov.movie_id = mov.id "
			     + "AND pmov.personaje_id = ?1", nativeQuery = true)
	List<String> findMovieByPersonajeId(Integer personajeID);

	Personaje findByNameIgnoreCase(String name);

	List<Personaje> findByYear(Integer year);

	List<Personaje> findByWeight(Double weight);

	@Query(value = "SELECT * FROM personajes pj, personaje_mov pmov, movies mov" 
	             +" WHERE pj.id = pmov.personaje_id "
		         + "AND mov.id = pmov.movie_id " 
	             + "AND mov.title = ?1 ", nativeQuery = true)
	List<Personaje> findByMovie(String title);

	
	
	
	
	
	
}
