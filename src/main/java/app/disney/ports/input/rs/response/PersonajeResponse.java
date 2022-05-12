package app.disney.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeResponse {

    private String name;

    @JsonProperty("img_personaje")
    private String imgPersonaje;

}
