package app.disney.domain.model;

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

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
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
	@JsonFormat(pattern = "yyyy-M-d", shape = JsonFormat.Shape.STRING)
	private LocalDate creationDate;

	@Column(name = "qualification")
	private Integer qualification;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn
	private Gender gender;

	public Movie(String imgMovie, String title, LocalDate creationDate, Integer qualification,
			Gender gender) {

		this.imgMovie = imgMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.gender = gender;

	}

	public Movie(String title, LocalDate date, int qualification, Gender gender) {

		this.title = title;
		this.creationDate = date;
		this.qualification = qualification;
		this.gender = gender;

	}

	public Movie(String title, LocalDate creationDate, Integer qualification) {

		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;

	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Movie movie = (Movie) o;

		if (!id.equals(movie.id)) return false;
		if (!imgMovie.equals(movie.imgMovie)) return false;
		if (!title.equals(movie.title)) return false;
		if (!creationDate.equals(movie.creationDate)) return false;
		if (!qualification.equals(movie.qualification)) return false;
		return gender.equals(movie.gender);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
