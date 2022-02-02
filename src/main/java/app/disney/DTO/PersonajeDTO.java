package app.disney.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PersonajeDTO {
	
	private Integer id;
	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	@Length(min = 2 , max = 50)
	private String name;
	
	private String imgPersonaje;
	
	@NotNull
	private Integer year;
	
	@NotNull
	private Integer weight;
	
	private List<MovieDTO> listMovieDTO;
	
	public PersonajeDTO() {}
	
	
	// contructor full parametros
	public PersonajeDTO(String name, String imgPersonaje, Integer year, Integer weight,
			List<MovieDTO> listMovieDTO) {
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
	
	
	// constructor sin imagen
	public PersonajeDTO(String name, Integer year, Integer weight, MovieDTO MovieDTO) {
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovieDTO = new ArrayList<MovieDTO>(Arrays.asList(MovieDTO));
	}
	
	

	
	
	

}
