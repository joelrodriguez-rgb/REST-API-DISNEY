package app.disney.domain.model;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class AppRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer id;

	@Column(name="name", nullable = false, updatable = false)
	private String name;
	
	public  AppRole (String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AppRole appRole = (AppRole) o;

		if (id != null ? !id.equals(appRole.id) : appRole.id != null) return false;
		return name != null ? name.equals(appRole.name) : appRole.name == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
