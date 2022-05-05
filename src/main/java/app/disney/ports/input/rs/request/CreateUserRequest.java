package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

	@NotNull
	@JsonProperty("first_name")
	private String firstName;

	@NotNull
	@JsonProperty("last_name")
	private String lastName;

	@Email
	@NotNull
	@JsonProperty("email")
	private String email;

	@NotNull
	@JsonProperty("password")
	@Size(min = 8, max = 12)
	private String password;
}
