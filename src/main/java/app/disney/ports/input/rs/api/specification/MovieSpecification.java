package app.disney.ports.input.rs.api.specification;

import app.disney.domain.model.Movie;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<Movie> getAllBySpec(MovieFilterRequest movie) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (movie.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + movie.getTitle() + "%"));

            } else if (movie.getIdGender() != null) {

                predicates.add(criteriaBuilder.equal(root.get("gender").get("id"), movie.getIdGender()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
