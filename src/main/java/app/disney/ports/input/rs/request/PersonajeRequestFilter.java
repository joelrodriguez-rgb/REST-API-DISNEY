package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequestFilter {

    private String name;

    private Integer age;

    private Integer weight;

    @JsonProperty("id_movie")
    private Long idMovie;

}
