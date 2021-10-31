package app.disney.entitys;

import java.util.ArrayList;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "personajes")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	private String name;

	
	@Column(name = "img_personaje")
	private String imgPersonaje;

	@Column(name = "year")
	private Integer year;

	@Column(name = "weight")
	private Integer weight;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "personaje_mov", joinColumns = @JoinColumn(name = "personaje_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> listMovie;

	public Personaje() {
	}

	public Personaje(String name, String imgPersonaje, Integer year, Integer weight, List<Movie> listMovie) {
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}

	public Personaje(String name, String imgPersonaje, Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;

		List<Movie> list = new ArrayList<>();
		list.add(movie);
		this.listMovie.add(movie);
	}

	public Personaje(String name, Integer year, Integer weight, List<Movie> listMovie) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}
	
	public Personaje(String name, Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.year = year;
		this.weight = weight;

		List<Movie> list = new ArrayList<>();
		list.add(movie);
		this.listMovie.add(movie);
	}
	
	
	public Personaje(String name, Integer year, Integer weight, String movie) {
		this.name = name;
		this.year = year;
		this.weight = weight;

	}
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPersonaje() {
		return imgPersonaje;
	}

	public void setImgPersonaje(String imgPersonaje) {
		this.imgPersonaje = imgPersonaje;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<Movie> getlistMovie() {
		return listMovie;
	}

	public void setlistMovie(List<Movie> listMovie) {
		this.listMovie = listMovie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return this.id;
	}



	@Override
	public String toString() {
		return "Personage [id=" + id + ", name=" + name + ", year=" + year + ", weight=" + weight + ", listMovie="
				+ listMovie + "]";
	}

}
