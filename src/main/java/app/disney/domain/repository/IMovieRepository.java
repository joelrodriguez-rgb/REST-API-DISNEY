package app.disney.domain.repository;

import app.disney.domain.model.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository
        extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {

    List<Movie> findAll(Specification<Movie> spec);

    Movie findByTitleIgnoreCase(String title);


    @Query(value = "SELECT * FROM movies "
            + "ORDER BY creation_date ASC", nativeQuery = true)
    List<Movie> findAllOrderByCreationDateAsc();


    @Query(value = "SELECT * FROM movies "
            + "ORDER BY creation_date DESC", nativeQuery = true)
    List<Movie> findAllOrderByCreationDateDesc();


    /**
     * Consulta auxiliar para traer para traer los personajes
     * relacionados a una pelicula
     */
    @Query(value = "SELECT name FROM personaje_mov pm "
            + "INNER JOIN personajes p "
            + "ON pm.personaje_id = p.id "
            + "INNER JOIN movies m "
            + "ON pm.movie_id = m.id "
            + "WHERE pm.movie_id = ?1", nativeQuery = true)
    List<String> findAllPersonajesByMovie(Integer id);

}
