package app.disney.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import app.disney.entitys.Movie;
import app.disney.entitys.Personaje;

@Component
public class PersonajeSpecification {

	public Specification<Personaje> getAllBySpec(Personaje personaje) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (personaje.getName() != null) {
				predicates.add(criteriaBuilder.like(root.get("name"), "%"+personaje.getName()+"%"));
				
			} else if (personaje.getYear() != null) {
				predicates.add(criteriaBuilder.equal(root.get("year"),personaje.getYear()));
				
			} else if (personaje.getWeight() != null) {
				predicates.add(criteriaBuilder.equal(root.get("weight"), personaje.getWeight()));
				
			}else if(!personaje.getListMovie().isEmpty()) {			
				Join<Personaje,Movie> joinMovie = root.join("listMovie");				
				predicates.add(criteriaBuilder.equal(joinMovie.get("id"), personaje.getListMovie().get(0).getId()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
