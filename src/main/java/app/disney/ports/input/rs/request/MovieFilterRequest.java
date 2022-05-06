package app.disney.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieFilterRequest {

    private String title;

    private Integer idGender;

    private String order;
}
