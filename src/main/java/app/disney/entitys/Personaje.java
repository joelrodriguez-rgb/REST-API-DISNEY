package app.disney.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personaje")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "personaje_name")
	private String personajeName;

	@Column(name = "img_personage")
	private String imgPersonaje;

	@Column(name = "year")
	private Integer year;

	@Column(name = "weight")
	private Double weight;

	@ManyToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
	@JoinTable(name = "personaje_mos", joinColumns = @JoinColumn(name = "personaje_id"), inverseJoinColumns = @JoinColumn(name = "mos_id"))
	private List<MovieOrSerie> listMOS;

	public Personaje() {
	}

	public Personaje(String personajeName, String imgPersonaje, Integer year, Double weight,
			List<MovieOrSerie> listMOS) {
		this.personajeName = personajeName;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;
		this.listMOS = listMOS;
	}

	public Personaje(String personajeName, String imgPersonaje, Integer year, Double weight, MovieOrSerie mos) {
		this.personajeName = personajeName;
		this.imgPersonaje = imgPersonaje;
		this.year = year;
		this.weight = weight;

		List<MovieOrSerie> list = new ArrayList<>();
		list.add(mos);
		this.listMOS.add(mos);
	}

	// contructor para probar jpa sin lista de peliculas
	public Personaje(String personajeName, Integer year, Double weight, MovieOrSerie mos) {
		this.personajeName = personajeName;
		this.year = year;
		this.weight = weight;

		List<MovieOrSerie> list = new ArrayList<MovieOrSerie>();
		list.add(mos);
		this.listMOS = list;
	}
	
	// contructor para probar jpa con lista de peliculas sin imagen
	public Personaje(String personajeName, Integer year, Double weight, List<MovieOrSerie> listMOS) {
		this.personajeName = personajeName;
		this.year = year;
		this.weight = weight;
		this.listMOS = listMOS;
	}

	
	
	

	public String getpersonajeName() {
		return personajeName;
	}

	public void setpersonajeName(String personajeName) {
		this.personajeName = personajeName;
	}

	public String getImgPersonaje() {
		return imgPersonaje;
	}

	public void setImgPersonaje(String imgPersonaje) {
		this.imgPersonaje = imgPersonaje;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public List<MovieOrSerie> getListMOS() {
		return listMOS;
	}

	public void setListMOS(List<MovieOrSerie> listMOS) {
		this.listMOS = listMOS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Personaje other = (Personaje) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (personajeName == null) {
			if (other.personajeName != null)
				return false;
		} else if (!personajeName.equals(other.personajeName))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personage [id=" + id + ", personajeName=" + personajeName + ", year=" + year + ", weight=" + weight
				+ ", listMOS=" + listMOS + "]";
	}

}
