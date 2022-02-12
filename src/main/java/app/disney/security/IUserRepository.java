package app.disney.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Integer> {

	AppUser findByUserName(String name);
}
