package app.disney.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class AppUser {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", updatable = false)
	private Integer id;
	
	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, updatable = false, unique = true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "role_id")
	@ToString.Exclude
	private Collection<AppRole> roles = new ArrayList<>();
	
	
	public AppUser(String userName, String paswsord, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AppUser appUser = (AppUser) o;

		if (id != null ? !id.equals(appUser.id) : appUser.id != null) return false;
		if (userName != null ? !userName.equals(appUser.userName) : appUser.userName != null) return false;
		if (password != null ? !password.equals(appUser.password) : appUser.password != null) return false;
		if (email != null ? !email.equals(appUser.email) : appUser.email != null) return false;
		return roles != null ? roles.equals(appUser.roles) : appUser.roles == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
