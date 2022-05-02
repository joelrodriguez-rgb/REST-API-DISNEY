package app.disney.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personaje")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personaje_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "img_personaje")
	private String imgPersonaje;

	@Column(name = "year_p")
	private Integer year;

	@Column(name = "weight")
	private Integer weight;

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "personaje_mov",
			joinColumns = @JoinColumn(name = "personaje_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> listMovie;


	// contructor full parametros
	public Personaje(String name, String imgPersonaje, Integer year, Integer weight,
					 List<Movie> listMovie) {
		super();
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}

	// constructor con 1 sola pelicula asociada con imagen
	public Personaje(String name, String imgPersonaje, Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovie = new ArrayList<Movie>(Arrays.asList(movie));
	}

	public Personaje(String name, Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovie = new ArrayList<Movie>(Arrays.asList(movie));
	}


	public Personaje(String name, Integer year, Integer weight, List<Movie> listMovie) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Personaje personaje = (Personaje) o;

		if (!id.equals(personaje.id)) return false;
		if (!name.equals(personaje.name)) return false;
		if (!imgPersonaje.equals(personaje.imgPersonaje)) return false;
		if (!year.equals(personaje.year)) return false;
		if (!weight.equals(personaje.weight)) return false;
		return listMovie != null ? listMovie.equals(personaje.listMovie) : personaje.listMovie == null;
	}

	@Override
	public int hashCode() {
		 return Objects.hash(id);
	}
}