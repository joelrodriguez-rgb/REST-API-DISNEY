package app.disney.ports.input.rs.api.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import app.disney.domain.model.Movie;

@Component
public class MovieSpecification {

	public Specification<Movie> getAllBySpec(Movie movie) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (movie.getTitle() != null) {
				predicates.add(criteriaBuilder.like(root.get("title"), "%" + movie.getTitle() + "%"));

			} else if (movie.getGender() != null) {
						
				predicates.add(criteriaBuilder.equal(root.get("gender").get("genderName"), movie.getGender().getGenderName()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
