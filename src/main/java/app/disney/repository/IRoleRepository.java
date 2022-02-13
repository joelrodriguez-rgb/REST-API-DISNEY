package app.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.disney.entitys.AppRole;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole, Integer> {

	

	
}
