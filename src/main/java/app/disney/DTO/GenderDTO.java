package app.disney.DTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class GenderDTO {


	private Integer id;

	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	private String genderName;


	private String imgGender;

	public GenderDTO() {
	}
	

	public GenderDTO(Integer id, String genderName, String imgGender) {
		super();
		this.id = id;
		this.genderName = genderName;
		this.imgGender = imgGender;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getImgGender() {
		return imgGender;
	}

	public void setImgGender(String imgGender) {
		this.imgGender = imgGender;
	}
	
	
	@Override
	public int hashCode() {
		return  this.id;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenderDTO other = (GenderDTO) obj;
		return Objects.equals(genderName, other.genderName) && Objects.equals(id, other.id)
				&& Objects.equals(imgGender, other.imgGender);
	}


	@Override
	public String toString() {
		return "GenderDTO [id=" + id + ", genderName=" + genderName + ", imgGender=" + imgGender + "]";
	}
	
	
	
	
}
