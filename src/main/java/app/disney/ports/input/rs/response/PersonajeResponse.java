package app.disney.ports.input.rs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeResponse {

    private Integer id;

    private String name;

    private String imgPersonaje;

    private Integer year;

    private Integer weight;
}
