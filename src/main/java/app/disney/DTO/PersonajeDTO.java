package app.disney.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonajeDTO {
	
	private Integer id;
	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	private String name;
	
	private String imgPersonaje;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private Integer weight;
	
	private List<MovieDTO> listMovieDTO;
	
	public PersonajeDTO() {}
	
	
	// contructor full parametros
	public PersonajeDTO(Integer id, String name, String imgPersonaje, Integer year, Integer weight,
			List<MovieDTO> listMovieDTO) {
		this.id = id;
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovieDTO = listMovieDTO;
	}
	
	// constructor con 1 sola pelicula asociada
	public PersonajeDTO(String name, String imgPersonaje, Integer year, Integer weight, MovieDTO MovieDTO) {
		this.name = name;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMovieDTO = new ArrayList<MovieDTO>(Arrays.asList(MovieDTO));
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
	public List<MovieDTO> getListMovieDTO() {
		return listMovieDTO;
	}
	public void setListMovieDTO(List<MovieDTO> listMovieDTO) {
		this.listMovieDTO = listMovieDTO;
	}
	
	
	
	

}
