package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequestFilter {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("idMovie")
    private Long idMovie;

}
