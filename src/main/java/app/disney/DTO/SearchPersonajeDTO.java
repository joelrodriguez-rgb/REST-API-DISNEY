package app.disney.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchPersonajeDTO {

	private String name;
	private Integer year;
	private Integer weight;
	private List<MovieDTO> listMovieDTO;

	public SearchPersonajeDTO(String name, Integer year, Integer weight, MovieDTO movieDTO) {
		super();
		this.name = name;
		this.year = year;
		this.weight = weight;
		this.listMovieDTO= new ArrayList<MovieDTO>(Arrays.asList(movieDTO));
	}

	public SearchPersonajeDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
