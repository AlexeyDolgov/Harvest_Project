package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "squash_gardenbed")
public class SquashGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Integer numberOfPlants;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private SquashVarietySpecification squashVarietySpecification;

	public SquashGardenbed() {
		super();
	}

	public SquashGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public SquashGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public SquashGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public SquashGardenbed(Place place) {
		super(place);
	}

	public SquashGardenbed(Integer id, String comment, Place place, Integer numberOfPlants, Double width,
			Double length) {
		super(id, comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public SquashGardenbed(Integer id, Place place, Integer numberOfPlants, Double width, Double length) {
		super(id, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public SquashGardenbed(String comment, Place place, Integer numberOfPlants, Double width, Double length) {
		super(comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public SquashGardenbed(Place place, Integer numberOfPlants, Double width, Double length) {
		super(place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public Integer getNumberOfPlants() {
		return numberOfPlants;
	}

	public void setNumberOfPlants(Integer numberOfPlants) {
		this.numberOfPlants = numberOfPlants;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public SquashVarietySpecification getSquashVarietySpecification() {
		return squashVarietySpecification;
	}

	public void setSquashVarietySpecification(SquashVarietySpecification squashVarietySpecification) {
		this.squashVarietySpecification = squashVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((numberOfPlants == null) ? 0 : numberOfPlants.hashCode());
		result = prime * result
				+ ((squashVarietySpecification == null) ? 0 : squashVarietySpecification.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquashGardenbed other = (SquashGardenbed) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (numberOfPlants == null) {
			if (other.numberOfPlants != null)
				return false;
		} else if (!numberOfPlants.equals(other.numberOfPlants))
			return false;
		if (squashVarietySpecification == null) {
			if (other.squashVarietySpecification != null)
				return false;
		} else if (!squashVarietySpecification.equals(other.squashVarietySpecification))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Comment=" + getComment() + ", Place=" + getPlace() + ", NumberOfPlants="
				+ numberOfPlants + ", Width=" + width + ", Length=" + length;
	}
}
