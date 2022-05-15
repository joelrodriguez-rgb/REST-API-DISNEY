package app.disney.domain.usercase.impl;

import app.disney.common.exceptions.handler.ExistingNameException;
import app.disney.common.exceptions.handler.NotFoundException;
import app.disney.domain.model.Movie;
import app.disney.domain.model.Personaje;
import app.disney.domain.repository.IMovieRepository;
import app.disney.domain.repository.IPersonajeRepository;
import app.disney.domain.usercase.IPersonajeService;
import app.disney.ports.input.rs.api.specification.PersonajeSpecification;
import app.disney.ports.input.rs.request.PersonajeRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Personaje> getAllPersonajesByFilter(PersonajeRequestFilter request) {

        Specification<Personaje> personajeSpec = spec.getAllBySpec(request);
        return personajeRepo.findAll(personajeSpec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> getAllPersonajes() {
        return personajeRepo.findAll();
    }

    @Override
    @Transactional
    public Long savePersonaje(Personaje personaje) {
        validateName(personaje.getName());
        return personajeRepo.save(personaje).getId();
    }

    @Override
    @Transactional
    public Personaje updatePersonaje(Long id, Personaje upPersonaje) {

        Personaje personaje = personajeRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

        personaje.setName(upPersonaje.getName());
        personaje.setAge(upPersonaje.getAge());
        personaje.setWeight(upPersonaje.getWeight());
        personaje.setImgPersonaje(upPersonaje.getImgPersonaje());

        return personajeRepo.save(personaje);
    }

    @Override
    @Transactional
    public void validateName(String name) {
        if (personajeRepo.findByNameIgnoreCase(name) != null)
            throw new ExistingNameException("NAME  : " + name);
    }


    @Override
    public void deletePersonajeById(Long id) {

        if (personajeRepo.findById(id).isPresent()) personajeRepo.deleteById(id);
        else throw new NotFoundException("ID : " + id);

    }
    @Override
    public List<Movie> getMovies(List<Long> movies) {

        List<Movie> listMovie = new ArrayList<>();
        movies.forEach(mov -> listMovie.add(movieRepository.findById(mov).orElseThrow(()->new NotFoundException(mov))));

        return listMovie;
    }
}
