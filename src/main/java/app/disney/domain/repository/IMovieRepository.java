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
        extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    List<Movie> findAll(Specification<Movie> spec);

    Movie findByTitleIgnoreCase(String title);


    /**
     * Consulta auxiliar para traer para traer los personajes
     * relacionados a una pelicula
     */
    @Query(value = "SELECT name FROM personaje p "
            + "INNER JOIN movie m "
            + "ON p.movie_id = m.movie_id "
            + "WHERE p.movie_id = ?1", nativeQuery = true)
    List<String> findAllPersonajesByMovie(Long id);

}
