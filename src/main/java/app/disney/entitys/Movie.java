package app.disney.entitys;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "img_mov")
	private String imgMovie;

	@Column(name = "title")
	private String title;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy-M-d")
	@JsonFormat(pattern = "yyyy-M-d" ,shape = JsonFormat.Shape.STRING)
	private LocalDate creationDate;

	@Column(name = "qualification")
	private Integer qualification;

	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn
	private Gender gender;
	

	public Movie() {
	}

	public Movie(String imgMovie, String title, LocalDate creationDate, Integer qualification, Gender gender) {
		this.imgMovie = imgMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.gender = gender;
	}
	
	
	public Movie(String title, LocalDate date, int qualfication, Gender gender) {
		this.title = title;
		this.creationDate = date;
		this.qualification = qualfication;
		this.gender = gender;
	}
	
	public Movie( String title, LocalDate creationDate, Integer qualification) {
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
	}
	
	

}
