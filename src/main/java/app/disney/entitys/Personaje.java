package app.disney.entitys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@Entity
@Table(name = "personajes")
@Data
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "img_personaje")
	private String imgPersonaje;

	@Column(name = "year")
	private Integer year;

	@Column(name = "weight")
	private Integer weight;

	@ManyToMany(cascade = { CascadeType.MERGE})
	@JoinTable(name = "personaje_mov", 
	           joinColumns = @JoinColumn(name = "personaje_id"), 
	           inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> listMovie;

	public Personaje() {
	}

	// contructor full parametros
	public Personaje( String name, String imgPersonaje, Integer year, Integer weight,
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
	
	public Personaje(String name,  Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovie = new ArrayList<Movie>(Arrays.asList(movie));
	}
	
	public Personaje(String name,  Integer year, Integer weight, List<Movie> listMovie) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}

}
