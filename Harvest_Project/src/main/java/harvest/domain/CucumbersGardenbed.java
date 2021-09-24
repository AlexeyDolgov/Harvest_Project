package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cucumbers_gardenbed")
public class CucumbersGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private CucumbersVarietySpecification cucumbersVarietySpecification;

	public CucumbersGardenbed() {
		super();
	}

	public CucumbersGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public CucumbersGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public CucumbersGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public CucumbersGardenbed(Place place) {
		super(place);
	}

	public CucumbersGardenbed(Integer id, String comment, Place place, Double width,
			Double length) {
		super(id, comment, place);
		this.width = width;
		this.length = length;
	}

	public CucumbersGardenbed(Integer id, Place place, Double width, Double length) {
		super(id, place);
		this.width = width;
		this.length = length;
	}

	public CucumbersGardenbed(String comment, Place place, Double width, Double length) {
		super(comment, place);
		this.width = width;
		this.length = length;
	}

	public CucumbersGardenbed(Place place, Double width, Double length) {
		super(place);
		this.width = width;
		this.length = length;
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

	public CucumbersVarietySpecification getCucumbersVarietySpecification() {
		return cucumbersVarietySpecification;
	}

	public void setCucumbersVarietySpecification(CucumbersVarietySpecification cucumbersVarietySpecification) {
		this.cucumbersVarietySpecification = cucumbersVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((cucumbersVarietySpecification == null) ? 0 : cucumbersVarietySpecification.hashCode());
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
		CucumbersGardenbed other = (CucumbersGardenbed) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (cucumbersVarietySpecification == null) {
			if (other.cucumbersVarietySpecification != null)
				return false;
		} else if (!cucumbersVarietySpecification.equals(other.cucumbersVarietySpecification))
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
		return "Id=" + getId() + ", Comment=" + getComment() + ", Place=" + getPlace() + ", Width=" + width + ", Length=" + length;
	}
}
