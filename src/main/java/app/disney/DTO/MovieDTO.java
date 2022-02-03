package app.disney.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.disney.entitys.Gender;
import app.disney.entitys.Movie;
import lombok.Data;


@Data
public class MovieDTO {
	
	
	private Integer id;

	private String imgMovie;

	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	@Length(min = 2 , max = 50)
	private String title;


	@NotNull
	@DateTimeFormat(pattern = "yyyy-M-d")
	@JsonFormat(pattern = "yyyy-M-d" ,shape = JsonFormat.Shape.STRING)
	private LocalDate creationDate;

	private Integer qualification;

	private Gender gender;
	
	
	public MovieDTO() {
	}


	
	public MovieDTO( String imgMovie, String title, LocalDate creationDate, Integer qualification,
			Gender gender) {
		this.imgMovie = imgMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.gender = gender;
	}
	
	public MovieDTO( String title, LocalDate creationDate, Integer qualification) {
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
	}
	
	
	public MovieDTO(Movie movie) {
		this.imgMovie = movie.getImgMovie();
		this.creationDate = movie.getCreationDate();
		this.gender = movie.getGender();
		this.qualification = movie.getQualification();
	}
	
	public MovieDTO( String title) {
		this.title = title;
	}
	



}
