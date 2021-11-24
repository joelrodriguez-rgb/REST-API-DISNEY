package app.disney.DTO;

public class SearchMovieDTO {
	
	private String title;
	
	private GenderDTO gender;

	public SearchMovieDTO(String title, GenderDTO gender) {
		this.title = title;
		this.gender = gender;
	}
	
	
	public SearchMovieDTO() {
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public GenderDTO getGender() {
		return gender;
	}


	public void setGender(GenderDTO gender) {
		this.gender = gender;
	}
	
	
	

}
