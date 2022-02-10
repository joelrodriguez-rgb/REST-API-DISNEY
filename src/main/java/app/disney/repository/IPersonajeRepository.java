package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import app.disney.entitys.Personaje;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Integer>,
                                              JpaSpecificationExecutor<Personaje> {
	
	List<Personaje> findAll(Specification<Personaje> spec);
	
	Personaje findByNameIgnoreCase(String name);
	
}
