package app.disney.DTO;

import lombok.Data;



@Data
public class SearchPersonajeDTO {

	private String name;
	private Integer year;
	private Integer weight;
	private MovieDTO movieDTO;
	
	public SearchPersonajeDTO() {
	}

	public SearchPersonajeDTO(String name, Integer year, Integer weight, MovieDTO movieDTO) {
		super();
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.movieDTO= movieDTO;
	}


}
