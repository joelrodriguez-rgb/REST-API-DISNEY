package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("image")
    private String imgMovie;

    @NotNull
    @JsonProperty("qualification")

    private Integer qualification;

    @NotNull
    @JsonProperty("creation_date")
    @DateTimeFormat(pattern = "yyyy-M-d")
    @JsonFormat(pattern = "yyyy-M-d", shape = JsonFormat.Shape.STRING)
    private LocalDate creationDate;

    @NotNull
    @JsonProperty("gender_id")
    private Integer idGender;
}
