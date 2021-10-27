package app.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.disney.entitys.MovieOrSerie;

@Repository
public interface IMosRepository extends JpaRepository<MovieOrSerie, Integer> {

}
