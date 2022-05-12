package app.disney.ports.input.rs.api.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import app.disney.ports.input.rs.request.PersonajeRequestFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;

@Component
public class PersonajeSpecification {

	public Specification<Personaje> getAllBySpec(PersonajeRequestFilter personaje) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (personaje.getName() != null) {
				predicates.add(criteriaBuilder.like(root.get("name"), "%"+personaje.getName()+"%"));
				
			} else if (personaje.getAge() != null) {
				predicates.add(criteriaBuilder.equal(root.get("year"),personaje.getAge()));
				
			} else if (personaje.getWeight() != null) {
				predicates.add(criteriaBuilder.equal(root.get("weight"), personaje.getWeight()));
				
			}else if(personaje.getIdMovie() != null) {
				Join<Personaje,Movie> joinMovie = root.join("listMovie");				
				predicates.add(criteriaBuilder.equal(joinMovie.get("id"), personaje.getIdMovie()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
