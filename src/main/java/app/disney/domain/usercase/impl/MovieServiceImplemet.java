package app.disney.domain.usercase.impl;

import app.disney.common.exceptions.handler.ExistingNameException;
import app.disney.common.exceptions.handler.NotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImplemet implements IMovieService {

    private final IMovieRepository movieRepository;
    private final IGenderRepository genderRepository;
    private final MovieSpecification spec;

/*	@Override
	public List<?> getListMovies(Movie request) {

		if (searchMovieDTO.getTitle() == null && searchMovieDTO.getGender() == null) {

			List<?> listMovieDTO = mapping.mappingListMovie(movieRepo.findAll());
			return listMovieDTO;

		} else {

			Movie movie = mapping.mappingSearchMovieToEntity(searchMovieDTO);
			Specification<Movie> movieSpec = spec.getAllBySpec(movie);
			List<Movie> listMovieBySpec = movieRepo.findAll(movieSpec);
			List<?> list = mapping.mappingListMovie(listMovieBySpec);

			return list;

		}

	}*/

    @Override
    @Transactional
    public Integer saveMovie(Movie movie) {

        validateName(movie.getTitle());
        movie.setGender(genderRepository.findByGenderName(movie.getGender().getGenderName()));

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
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("ID : " + id));
    }

    @Override
    @Transactional
    public void deleteMovieById(Integer id) {
        if (movieRepository.findById(id).isPresent()) movieRepository.deleteById(id);
        else throw new NotFoundException("ID : " + id);
    }

    @Override
    public Movie getByTitleIgnoreCase(String title) {
        return movieRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public String saveImg(MultipartFile imagen) {

        if (imagen != null) {

            Path directorioImagenes = Paths.get("src//main//resources//static/imgMovies");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                return imagen.getOriginalFilename();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies(MovieFilterRequest request, String order) {

        Specification<Movie> movieSpec = spec.getAllBySpec(request);
        List<Movie> list = movieRepository.findAll(movieSpec);

        /*list.sort(Comparator.comparing(o -> o.getCreationDate()));
        Collections.sort(list, Collections.reverseOrder());*/

        if (order == "ASC")
            Collections.sort(list, Comparator.comparing(Movie::getCreationDate));
        else
            Collections.sort(list, Comparator.comparing(Movie::getCreationDate).reversed());

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies(String order) {

        List<Movie> list = movieRepository.findAll();

        if (order == "ASC")
            Collections.sort(list, Comparator.comparing(Movie::getCreationDate));
        else
            Collections.sort(list, Comparator.comparing(Movie::getCreationDate).reversed());

        return list;
    }


    @Override
    public List<String> getAllPersonajesByMovie(Integer id) {

        List<String> listPersonaje = movieRepository.findAllPersonajesByMovie(id);

        return listPersonaje;

    }

}
