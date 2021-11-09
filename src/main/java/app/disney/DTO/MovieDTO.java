package app.disney.DTO;

import java.time.LocalDate;

import app.disney.entitys.Gender;
import lombok.Data;


public class MovieDTO {
	
	
	private Integer id;

	private String imgMovie;

	private String title;

	private LocalDate creationDate;

	private Integer qualification;

	private Gender gender;
	
	
	public MovieDTO() {
	}

	public MovieDTO( String title) {
		this.title = title;
	}
	
	public MovieDTO(Integer id, String imgMovie, String title, LocalDate creationDate, Integer qualification,
			Gender gender) {
		super();
		this.id = id;
		this.imgMovie = imgMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgMovie() {
		return imgMovie;
	}

	public void setImgMovie(String imgMovie) {
		this.imgMovie = imgMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}
