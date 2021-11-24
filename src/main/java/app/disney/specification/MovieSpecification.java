package app.disney.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import app.disney.entitys.Movie;

@Component
public class MovieSpecification {

	public Specification<Movie> getAllBySpec(Movie movie) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (!movie.getTitle().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("title"), "%" + movie.getTitle() + "%"));

			} else if (movie.getGender() != null) {
				predicates.add(criteriaBuilder.equal(root.get("gender"), movie.getGender()));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
