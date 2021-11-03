package app.disney.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.disney.entitys.Movie;
import lombok.Data;

@Data
public class PersonajeDTO {
	
	private Integer id;
	private String name;
	private String imgPersonaje;
	private Integer year;
	private Integer weight;
	private List<Movie> listMovie;
	
	public PersonajeDTO() {}
	
	
	// contructor full parametros
	public PersonajeDTO(Integer id, String name, String imgPersonaje, Integer year, Integer weight,
			List<Movie> listMovie) {
		super();
		this.id = id;
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovie = listMovie;
	}
	
	// constructor con 1 sola pelicula asociada
	public PersonajeDTO(String name, String imgPersonaje, Integer year, Integer weight, Movie movie) {
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovie = new ArrayList<Movie>(Arrays.asList(movie));
	}
	
	
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<Movie> getListMovie() {
		return listMovie;
	}
	public void setListMovie(List<Movie> listMovie) {
		this.listMovie = listMovie;
	}
	
	
	
	

}
