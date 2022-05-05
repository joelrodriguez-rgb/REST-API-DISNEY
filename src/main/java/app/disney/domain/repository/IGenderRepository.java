package app.disney.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import app.disney.domain.model.Gender;
@Repository
public interface IGenderRepository extends JpaRepository<Gender, Integer>,JpaSpecificationExecutor<Gender>  {
	Gender findByGenderName(String name);
}
