package app.disney.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gender")
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
	
//Constructor para probar jpa
	public Gender(String genderName) {
		this.genderName = genderName;
	}
	
	
	public Gender(String genderName, String imgGender) {
		this.genderName = genderName;
		this.imgGender = imgGender;
	}
	
	public Gender(Gender genderExisting) {
		this.genderName = genderExisting.getGenderName();
		this.imgGender = genderExisting.getImgGender();
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
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gender other = (Gender) obj;
		if (genderName == null) {
			if (other.genderName != null)
				return false;
		} else if (!genderName.equals(other.genderName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gender [id=" + id + ", genderName=" + genderName + "]";
	}
	
	
	

}
