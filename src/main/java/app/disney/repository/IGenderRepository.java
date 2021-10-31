package app.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import app.disney.entitys.Gender;
@Repository
public interface IGenderRepository extends JpaRepository<Gender, Integer>,JpaSpecificationExecutor<Gender>  {

	
	Gender findByGenderName(String name);
}
