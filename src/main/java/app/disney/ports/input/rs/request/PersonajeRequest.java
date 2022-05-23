package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("img_personaje")
    private String imgPersonaje;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("list_movies")
    private Integer weight;

}
