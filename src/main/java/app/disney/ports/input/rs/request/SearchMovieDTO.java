package app.disney.ports.input.rs.request;

import lombok.Data;

@Data
public class SearchMovieDTO {
	
	private String title;
	
	private GenderDTO gender;
	
	
	
	public SearchMovieDTO() {
	}

	public SearchMovieDTO(String title, GenderDTO gender) {
		this.title = title;
		this.gender = gender;
	}
	

}
