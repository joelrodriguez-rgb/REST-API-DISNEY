package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieFilterRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("idGender")
    private Long idGender;

    @JsonProperty("order")
    private String order;
}
