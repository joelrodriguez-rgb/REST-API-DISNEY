package app.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.disney.entitys.AppUser;

public interface IUserRepository extends JpaRepository<AppUser, Integer> {

	AppUser findByUserName(String name);
}
