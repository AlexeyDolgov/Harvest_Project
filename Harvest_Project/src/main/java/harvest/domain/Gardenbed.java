package harvest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Gardenbed {

	@Id
	@SequenceGenerator(name = "gardenbedSequence", sequenceName = "sequence_gardenbed", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gardenbedSequence")
	@Column(name = "gardenbed_id")
	private Integer id;

	@Column
	private String comment;

	@Column(nullable = false)
	@NotBlank(message = "Значение поля не может быть пустым!")
	@Enumerated(EnumType.STRING)
	private Place place;

	public Gardenbed() {
	}

	public Gardenbed(Integer id, String comment, Place place) {
		this.id = id;
		this.comment = comment;
		this.place = place;
	}

	public Gardenbed(Integer id, Place place) {
		this.id = id;
		this.place = place;
	}

	public Gardenbed(String comment, Place place) {
		this.comment = comment;
		this.place = place;
	}

	public Gardenbed(Place place) {
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gardenbed other = (Gardenbed) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (place != other.place)
			return false;
		return true;
	}
}
