package app.disney.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "personaje")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personaje_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "img_personaje")
    private String imgPersonaje;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "personaje_movie",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> listMovie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personaje personaje = (Personaje) o;

        if (!id.equals(personaje.id)) return false;
        if (!name.equals(personaje.name)) return false;
        if (!imgPersonaje.equals(personaje.imgPersonaje)) return false;
        if (!age.equals(personaje.age)) return false;
        if (!weight.equals(personaje.weight)) return false;
        return listMovie != null ? listMovie.equals(personaje.listMovie) : personaje.listMovie == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}