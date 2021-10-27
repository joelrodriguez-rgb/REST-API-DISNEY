package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.disney.entitys.Personaje;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Integer> {


	@Query(value ="SELECT mov.title FROM personaje_mos pmov, movie_or_serie mov "
			+ "WHERE pmov.mos_id = mov.id "
			+ "AND pmov.personaje_id = ?1", nativeQuery = true)
	List<String> findMovieByPersonajeId(Integer personajeID);
	
	Personaje findFirstByPersonajeNameIgnoreCase(String name);
}
