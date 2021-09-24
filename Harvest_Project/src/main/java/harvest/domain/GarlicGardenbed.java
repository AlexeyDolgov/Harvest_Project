package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "garlic_gardenbed")
public class GarlicGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Integer numberOfPlants;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private GarlicVarietySpecification garlicVarietySpecification;

	public GarlicGardenbed() {
		super();
	}

	public GarlicGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public GarlicGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public GarlicGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public GarlicGardenbed(Place place) {
		super(place);
	}

	public GarlicGardenbed(Integer id, String comment, Place place, Integer numberOfPlants, Double width,
			Double length) {
		super(id, comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public GarlicGardenbed(Integer id, Place place, Integer numberOfPlants, Double width, Double length) {
		super(id, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public GarlicGardenbed(String comment, Place place, Integer numberOfPlants, Double width, Double length) {
		super(comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public GarlicGardenbed(Place place, Integer numberOfPlants, Double width, Double length) {
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

	public GarlicVarietySpecification getGarlicVarietySpecification() {
		return garlicVarietySpecification;
	}

	public void setGarlicVarietySpecification(GarlicVarietySpecification garlicVarietySpecification) {
		this.garlicVarietySpecification = garlicVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((numberOfPlants == null) ? 0 : numberOfPlants.hashCode());
		result = prime * result
				+ ((garlicVarietySpecification == null) ? 0 : garlicVarietySpecification.hashCode());
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
		GarlicGardenbed other = (GarlicGardenbed) obj;
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
		if (garlicVarietySpecification == null) {
			if (other.garlicVarietySpecification != null)
				return false;
		} else if (!garlicVarietySpecification.equals(other.garlicVarietySpecification))
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
