package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequest {

    private String name;

    private String imgPersonaje;

    private Integer year;

    private Integer weight;

    @JsonProperty("list_movies")
    private List<String> moviesTitle;
}
