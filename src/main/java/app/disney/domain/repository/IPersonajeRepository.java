package app.disney.domain.repository;

import app.disney.domain.model.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Integer>,
        JpaSpecificationExecutor<Personaje> {

    List<Personaje> findAll(Specification<Personaje> spec);

    Personaje findByNameIgnoreCase(String name);


    /**
     * Consulta auxiliar para traer para traer las peliculas
     * relacionados a un personaje
     */
    @Query(value = "SELECT title FROM personaje_mov pm "
            + "INNER JOIN personajes p "
            + "ON pm.personaje_id = p.id "
            + "INNER JOIN movies m "
            + "ON pm.movie_id = m.id "
            + "WHERE pm.personaje_id = ?1", nativeQuery = true)
    List<String> findAllMoviesByPersonajeId(Integer id);

}
