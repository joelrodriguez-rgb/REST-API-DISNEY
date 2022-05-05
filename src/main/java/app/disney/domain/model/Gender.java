package app.disney.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gender_name")
    private String genderName;

    @Column(name = "img_gender")
    private String imgGender;


    public Gender(String genderName, String imgGender) {
        super();
        this.genderName = genderName;
        this.imgGender = imgGender;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gender gender = (Gender) o;

        if (id != null ? !id.equals(gender.id) : gender.id != null) return false;
        if (genderName != null ? !genderName.equals(gender.genderName) : gender.genderName != null) return false;
        return imgGender != null ? imgGender.equals(gender.imgGender) : gender.imgGender == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
