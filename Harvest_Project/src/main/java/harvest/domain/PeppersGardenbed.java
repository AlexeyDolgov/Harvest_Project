package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "peppers_gardenbed")
public class PeppersGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Integer numberOfPlants;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private PeppersVarietySpecification peppersVarietySpecification;

	public PeppersGardenbed() {
		super();
	}

	public PeppersGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public PeppersGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public PeppersGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public PeppersGardenbed(Place place) {
		super(place);
	}

	public PeppersGardenbed(Integer id, String comment, Place place, Integer numberOfPlants, Double width,
			Double length) {
		super(id, comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public PeppersGardenbed(Integer id, Place place, Integer numberOfPlants, Double width, Double length) {
		super(id, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public PeppersGardenbed(String comment, Place place, Integer numberOfPlants, Double width, Double length) {
		super(comment, place);
		this.numberOfPlants = numberOfPlants;
		this.width = width;
		this.length = length;
	}

	public PeppersGardenbed(Place place, Integer numberOfPlants, Double width, Double length) {
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

	public PeppersVarietySpecification getPeppersVarietySpecification() {
		return peppersVarietySpecification;
	}

	public void setPeppersVarietySpecification(PeppersVarietySpecification peppersVarietySpecification) {
		this.peppersVarietySpecification = peppersVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((numberOfPlants == null) ? 0 : numberOfPlants.hashCode());
		result = prime * result
				+ ((peppersVarietySpecification == null) ? 0 : peppersVarietySpecification.hashCode());
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
		PeppersGardenbed other = (PeppersGardenbed) obj;
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
		if (peppersVarietySpecification == null) {
			if (other.peppersVarietySpecification != null)
				return false;
		} else if (!peppersVarietySpecification.equals(other.peppersVarietySpecification))
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
