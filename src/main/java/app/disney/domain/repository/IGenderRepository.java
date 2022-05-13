package app.disney.domain.repository;

import app.disney.domain.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Long> {
    Gender findByGenderName(String name);
}
