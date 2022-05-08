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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImplemet implements IMovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImplemet.class);

    private final IMovieRepository movieRepository;
    private final IGenderRepository genderRepository;
    private final MovieSpecification spec;

    @Override
    @Transactional
    public Integer saveMovie(Movie movie) {

        validateName(movie.getTitle());
        Gender gender = genderRepository.findByGenderName(movie.getGender().getGenderName());
        movie.setGender(gender);

        return movieRepository.save(movie).getId();
    }

    @Override
    @Transactional
    public void upDateMovie(Integer id, Movie upMovie) {

        Movie movieExisting = movieRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        validateName(upMovie.getTitle());

        movieExisting.setTitle(upMovie.getTitle());
        movieExisting.setCreationDate(upMovie.getCreationDate());
        movieExisting.setQualification(upMovie.getQualification());
        movieExisting.setImgMovie(upMovie.getImgMovie());
        movieExisting.setGender(genderRepository.findByGenderName(upMovie.getGender().getGenderName()));

        movieRepository.save(movieExisting);
    }

    @Override
    public void validateName(String title) {

        if (movieRepository.findByTitleIgnoreCase(title) != null)
            throw new ExistingNameException("TITLE  : " + title);

    }

    @Override
    @Transactional
    public void deleteMovieById(Integer id) {
        if (movieRepository.findById(id).isPresent()) movieRepository.deleteById(id);
        else throw new NotFoundException("ID : " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies(MovieFilterRequest request) {

        Specification<Movie> movieSpec = spec.getAllBySpec(request);
        List<Movie> list = movieRepository.findAll(movieSpec);


        if (request.getOrder().equals("ASC"))
            //  list = movieRepository.findAll(Sort.by("creation_date"));
            list.sort(Comparator.comparing(Movie::getCreationDate));
        else if (request.getOrder().equals("DESC"))
            //list = movieRepository.findAll(Sort.by("creation_date").descending());
            list.sort(Comparator.comparing(Movie::getCreationDate).reversed());

        return list;
    }
}
