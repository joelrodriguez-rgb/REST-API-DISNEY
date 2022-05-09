package app.disney.domain.repository;

import app.disney.domain.model.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Long>,
        JpaSpecificationExecutor<Personaje> {

    List<Personaje> findAll(Specification<Personaje> spec);

    Personaje findByNameIgnoreCase(String name);


    /**
     * Consulta auxiliar para traer para traer las peliculas
     * relacionados a un personaje
     */
    @Query(value = "SELECT title FROM personaje p "
            + "INNER JOIN movies m "
            + "ON p.movie_id = m.movie_id "
            + "WHERE p.personaje_id = ?1", nativeQuery = true)
    List<String> findAllMoviesByPersonajeId(Long id);

}
