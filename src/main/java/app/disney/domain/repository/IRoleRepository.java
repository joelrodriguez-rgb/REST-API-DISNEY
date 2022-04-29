package app.disney.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.disney.domain.model.AppRole;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole, Integer> {

	

	
}
