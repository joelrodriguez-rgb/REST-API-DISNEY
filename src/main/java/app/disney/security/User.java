package app.disney.security;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class User {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotBlank
	@NotEmpty
	private String userName;
	
	@Column
	@NotBlank
	@NotEmpty
	private String password;
	
	@Column
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
	private String email;
	
	

}
