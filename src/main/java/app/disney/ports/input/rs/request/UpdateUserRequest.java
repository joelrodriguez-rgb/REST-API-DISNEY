package app.disney.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @JsonProperty("first_name")
    private String firstName;


    @JsonProperty("last_name")
    private String lastName;


    @JsonProperty("password")
    @Size(min = 8, max = 12)
    private String password;

    private String photo;
}
