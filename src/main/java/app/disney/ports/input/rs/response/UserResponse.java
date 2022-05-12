package app.disney.ports.input.rs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String photo;
}
