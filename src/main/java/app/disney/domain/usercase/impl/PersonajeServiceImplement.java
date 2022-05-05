package app.disney.domain.usercase.impl;

import app.disney.common.exceptions.handler.ExistingNameException;
import app.disney.common.exceptions.handler.NotFoundException;
import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.domain.repository.IMovieRepository;
import app.disney.domain.repository.IPersonajeRepository;
import app.disney.domain.usercase.IPersonajeService;
import app.disney.ports.input.rs.api.specification.PersonajeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonajeServiceImplement implements IPersonajeService {

    private final IPersonajeRepository personajeRepo;

    private final IMovieRepository movieRepository;

    private final PersonajeSpecification spec;

    @Override
    @Transactional
    public List<Personaje> getPersonajes(Personaje request) {

        Specification<Personaje> personajeSpec = spec.getAllBySpec(request);
        List<Personaje> listPersonajeBySpec = personajeRepo.findAll(personajeSpec);

        return listPersonajeBySpec;
    }

    @Override
    @Transactional
    public Integer savePersonaje(Personaje personaje) {

        validateName(personaje.getName());

        personaje.setListMovie(getListMoviesByTitle(personaje.getListMovie()));
        ;

        return personajeRepo.save(personaje).getId();
    }

    @Override
    @Transactional
    public Personaje updatePersonaje(Integer id, Personaje upPersonaje) {

        Personaje personaje = personajeRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        personaje.setName(upPersonaje.getName());
        personaje.setYear(upPersonaje.getYear());
        personaje.setWeight(upPersonaje.getWeight());
        personaje.setImgPersonaje(upPersonaje.getImgPersonaje());
        personaje.setListMovie(getListMoviesByTitle(upPersonaje.getListMovie()));

        return personajeRepo.save(personaje);
    }

    @Override
    public Personaje getPersonajeById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public void validateName(String name) {
        if (personajeRepo.findByNameIgnoreCase(name) != null)
            throw new ExistingNameException("NAME  : " + name);
    }


    @Override
    public void deletePersonajeById(Integer id) {

        if (personajeRepo.findById(id).isPresent()) personajeRepo.deleteById(id);
        else throw new NotFoundException("ID : " + id);

    }

    @Override
    public String saveImg(MultipartFile imagen) {

        if (imagen != null) {

            Path directorioImagenes = Paths.get("src//main//resources//static/imgCharacters");
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
    public List<Movie> getListMoviesByTitle(List<Movie> listMovieTitle) {

        List<Movie> listMovie = new ArrayList<>();

        listMovieTitle.forEach(mov -> listMovie.add(movieRepository.findByTitleIgnoreCase(mov.getTitle())));

        return listMovie;
    }

    @Override
    public List<String> getAllMoviesByPersonajeId(Integer id) {
        return personajeRepo.findAllMoviesByPersonajeId(id);
    }

}
