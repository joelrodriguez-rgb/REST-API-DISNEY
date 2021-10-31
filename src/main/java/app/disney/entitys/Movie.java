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

@Entity
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate;

	@Column(name = "qualification")
	private Integer qualification;

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn
	private Gender gender;
	

	public Movie() {
	}

	public Movie(String imgMovie, String title, LocalDate creationDate, Integer qualification, Gender gender) {
		super();
		this.imgMovie = imgMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.gender = gender;
	}
	
	//contructor para probar jpa
	public Movie( String title, LocalDate creationDate, Integer qualification, Gender gender) {
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

	public String getimgMovie() {
		return imgMovie;
	}

	public void setimgMovie(String imgMovie) {
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

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie[id=" + id + ", title=" + title + ", creationDate=" + creationDate + ", qualification="
				+ qualification + ", gender=" + gender + "]";
	}
	
	
	

}
