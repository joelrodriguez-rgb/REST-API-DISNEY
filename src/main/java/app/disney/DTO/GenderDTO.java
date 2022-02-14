package app.disney.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class GenderDTO {

	private Integer id;

	@NotEmpty(message = "Este campo no puede estar vacio")
	@NotBlank(message = "Este campo no puede estar en blanco")
	private String genderName;

	private String imgGender;

	public GenderDTO() {}

	public GenderDTO(String genderName, String imgGender) {

		this.genderName = genderName;
		this.imgGender = imgGender;

	}

	public GenderDTO(String genderName) {

		this.genderName = genderName;

	}

}
