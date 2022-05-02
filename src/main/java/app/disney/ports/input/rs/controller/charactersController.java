package app.disney.ports.input.rs.controller;

import app.disney.domain.usercase.IPersonajeService;
import app.disney.util.IMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class charactersController {

    @Autowired
    private IPersonajeService personajeService;

    @Autowired
    private IMapper mapping;

    @GetMapping()
    public ResponseEntity<List<?>> listPersonaje(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "weight", required = false) Integer weight,
            @RequestParam(value = "title", required = false) String title) {

        MovieDTO movieDTO = new MovieDTO(title);
        SearchPersonajeDTO searchPersonajeDTO = new SearchPersonajeDTO(name, year, weight, movieDTO);
        List<?> listPersonajes = personajeService.getListPersonajes(searchPersonajeDTO);

        return new ResponseEntity<>(listPersonajes, HttpStatus.OK);
    }

    @GetMapping("/addCharacter")
    public ResponseEntity<?> addCharacter() {

        PersonajeDTO personajeDTO = new PersonajeDTO();
        return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
    }

    @GetMapping("/editCharacter/{id}")
    public ResponseEntity<?> editCharacter(@PathVariable Integer id) {

        PersonajeDTO personajeDTObyID = mapping.mappingPersonajeToDTO(personajeService.getPersonajeById(id));
        return new ResponseEntity<>(personajeDTObyID, HttpStatus.OK);
    }


    @GetMapping("/detailCharacter/{id}")
    public ResponseEntity<?> detailCharacter(@PathVariable Integer id) {

        PersonajeDTO personajeDTO = mapping.mappingPersonajeToDTO(personajeService.getPersonajeById(id));
        List<String> movies = personajeService.getAllMoviesByPersonajeId(id);
        JSONObject myPersonajeJSON = new JSONObject();
        myPersonajeJSON.put("Name", personajeDTO.getName());
        myPersonajeJSON.put("Year", personajeDTO.getYear());
        myPersonajeJSON.put("Weight", personajeDTO.getWeight());
        myPersonajeJSON.put("Imagen", personajeDTO.getImgPersonaje());
        myPersonajeJSON.put("Movies", movies);

        return new ResponseEntity<>(myPersonajeJSON.toString(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePersonaje(@PathVariable Integer id) {

        personajeService.deletePersonajeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/saveCharacter")
    public ResponseEntity<?> saveStudent(
            @RequestBody @Valid PersonajeDTO newPersonaje,
            BindingResult result,
            @RequestParam(value = "file", required = false) MultipartFile imagen,
            @RequestParam(value = "title") List<String> listMovieTitle) {

        if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        personajeService.savePersonaje(newPersonaje, imagen, listMovieTitle);

        return new ResponseEntity<>(newPersonaje, HttpStatus.CREATED);

    }

    @PutMapping("/editCharacter/{id}")
    public ResponseEntity<?> saveChangesPersonaje(
            @PathVariable Integer id,
            @RequestBody @Valid PersonajeDTO upPersonaje,
            BindingResult result,
            @RequestParam(value = "file", required = false) MultipartFile imagen,
            @RequestParam(value = "title") List<String> listMovieTitle) {

        if (result.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        personajeService.upDatePersonaje(upPersonaje, id, imagen, listMovieTitle);

        return new ResponseEntity<>(upPersonaje, HttpStatus.OK);
    }

}
