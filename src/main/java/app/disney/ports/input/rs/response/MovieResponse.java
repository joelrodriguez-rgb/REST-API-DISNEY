package app.disney.ports.input.rs.response;

import app.disney.domain.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private  String title;

    private  String imgMovie;

    private  LocalDate creationDate;

    private  Integer qualification;

    private Gender gender;

    private List<String> personajes;
}
