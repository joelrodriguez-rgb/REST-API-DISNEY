package app.disney.domain.repository;

import app.disney.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByEmail(String email);
}
