package app.disney.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.disney.domain.model.AppUser;

public interface IUserRepository extends JpaRepository<AppUser, Integer> {

	AppUser findByUserName(String name);
}
