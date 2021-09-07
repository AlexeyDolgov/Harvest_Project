package harvest.domain;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Variety {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "variety_id")
	private Integer id;

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private String name;

	@ElementCollection(targetClass = Place.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "place", joinColumns = @JoinColumn(name = "id"))
	@Enumerated(EnumType.STRING)
	private Set<Place> place;

	public Variety() {
	}

	public Variety(Integer id, String name, Set<Place> place) {
		this.id = id;
		this.name = name;
		this.place = place;
	}

	public Variety(String name, Set<Place> place) {
		this.name = name;
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Place> getPlace() {
		return place;
	}

	public void setPlace(Set<Place> place) {
		this.place = place;
	}
}
