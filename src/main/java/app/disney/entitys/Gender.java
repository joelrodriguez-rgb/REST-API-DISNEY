package app.disney.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "gender")
@Data
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "gender_name")
	private String genderName;

	@Column(name = "img_gender")
	private String imgGender;

	public Gender() {
	}
	

	public Gender(Integer id, String genderName, String imgGender) {
		super();
		this.id = id;
		this.genderName = genderName;
		this.imgGender = imgGender;
	}


}
