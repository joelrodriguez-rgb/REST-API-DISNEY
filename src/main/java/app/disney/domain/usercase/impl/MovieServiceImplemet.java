package app.disney.domain.usercase.impl;

import app.disney.common.exceptions.handler.ExistingNameException;
import app.disney.common.exceptions.handler.NotFoundException;
import app.disney.domain.model.Gender;
import app.disney.domain.model.Movie;
import app.disney.domain.repository.IGenderRepository;
import app.disney.domain.repository.IMovieRepository;
import app.disney.domain.usercase.IMovieService;
import app.disney.ports.input.rs.api.specification.MovieSpecification;
import app.disney.ports.input.rs.request.MovieFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImplemet implements IMovieService {

    private final IMovieRepository movieRepository;
    private final IGenderRepository genderRepository;
    private final MovieSpecification spec;

    @Override
    @Transactional
    public Long saveMovie(Movie movie) {

        validateName(movie.getTitle());
        Long idGender = movie.getGender().getId();
        Gender gender = genderRepository.findById(idGender).orElseThrow(()-> new NotFoundException(idGender));
        movie.setGender(gender);

        return movieRepository.save(movie).getId();
    }

    @Override
    @Transactional
    public void upDateMovie(Long id, Movie upMovie) {

        Movie movieExisting = movieRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        validateName(upMovie.getTitle());

        movieExisting.setTitle(upMovie.getTitle());
        movieExisting.setCreationDate(upMovie.getCreationDate());
        movieExisting.setQualification(upMovie.getQualification());
        movieExisting.setImgMovie(upMovie.getImgMovie());
        movieExisting.setGender(genderRepository.findByGenderName(upMovie.getGender().getGenderName()));

        movieRepository.save(movieExisting);
    }


    private void validateName(String title) {
        if (movieRepository.findByTitleIgnoreCase(title) != null)
            throw new ExistingNameException("TITLE  : " + title);
    }

    @Override
    @Transactional
    public void deleteMovieById(Long id) {
        if (movieRepository.findById(id).isPresent()) movieRepository.deleteById(id);
        else throw new NotFoundException("ID : " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMoviesByFilter(MovieFilterRequest request) {

        Specification<Movie> movieSpec = spec.getAllBySpec(request);
        List<Movie> list = movieRepository.findAll(movieSpec);

        if (request.getOrder() != null) sortList(list, request.getOrder());

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies(MovieFilterRequest request) {

        List<Movie> list = movieRepository.findAll();

        if (request.getOrder() != null) sortList(list, request.getOrder());

        return list;
    }

    @Override
    public Movie getMovie(Long id) {

        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<String> getPersonajesByMovie(Long id) {
        return movieRepository.findAllPersonajesByMovie(id);
    }

    private List<Movie> sortList(List<Movie> list, String order) {

        if (order.equals("ASC"))
            list.sort(Comparator.comparing(Movie::getCreationDate));
        else if (order.equals("DESC"))
            list.sort(Comparator.comparing(Movie::getCreationDate).reversed());

        return list;
    }


}
