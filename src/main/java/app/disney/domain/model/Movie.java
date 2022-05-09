package app.disney.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true, updatable = true)
    private String title;

    @Column(name = "img_movie")
    private String imgMovie;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-M-d")
    @JsonFormat(pattern = "yyyy-M-d", shape = JsonFormat.Shape.STRING)
    private LocalDate creationDate;

    @Column(name = "qualification")
    private Integer qualification;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!id.equals(movie.id)) return false;
        if (!imgMovie.equals(movie.imgMovie)) return false;
        if (!title.equals(movie.title)) return false;
        if (!creationDate.equals(movie.creationDate)) return false;
        if (!qualification.equals(movie.qualification)) return false;
        return gender.equals(movie.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
