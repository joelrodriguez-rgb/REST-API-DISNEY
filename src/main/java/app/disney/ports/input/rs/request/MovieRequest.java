package app.disney.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;

    private String imgMovie;

    private Integer qualification;

    private LocalDate creationDate;

    private String genderName;
}
