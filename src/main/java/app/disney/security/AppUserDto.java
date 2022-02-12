package app.disney.security;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
	
	@NotBlank
	@NotEmpty
	private String userName;
	
    @Size(min = 4, max = 20)
	private String password;
	
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
	private String email;
	
	private Collection<Role> roles;

}
