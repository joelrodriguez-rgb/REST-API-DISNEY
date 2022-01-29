package app.disney.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;



@Data
public class SearchPersonajeDTO {

	private String name;
	private Integer year;
	private Integer weight;
	private List<MovieDTO> listMovieDTO;
	
	public SearchPersonajeDTO() {
	}

	public SearchPersonajeDTO(String name, Integer year, Integer weight, MovieDTO movieDTO) {
		super();
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovieDTO= new ArrayList<MovieDTO>(Arrays.asList(movieDTO));
	}


}
