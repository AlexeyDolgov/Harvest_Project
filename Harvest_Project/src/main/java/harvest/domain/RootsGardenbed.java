package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roots_gardenbed")
public class RootsGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private RootsVarietySpecification rootsVarietySpecification;

	public RootsGardenbed() {
		super();
	}

	public RootsGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public RootsGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public RootsGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public RootsGardenbed(Place place) {
		super(place);
	}

	public RootsGardenbed(Integer id, String comment, Place place, Double width,
			Double length) {
		super(id, comment, place);
		this.width = width;
		this.length = length;
	}

	public RootsGardenbed(Integer id, Place place, Double width, Double length) {
		super(id, place);
		this.width = width;
		this.length = length;
	}

	public RootsGardenbed(String comment, Place place, Double width, Double length) {
		super(comment, place);
		this.width = width;
		this.length = length;
	}

	public RootsGardenbed(Place place, Double width, Double length) {
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

	public RootsVarietySpecification getRootsVarietySpecification() {
		return rootsVarietySpecification;
	}

	public void setRootsVarietySpecification(RootsVarietySpecification rootsVarietySpecification) {
		this.rootsVarietySpecification = rootsVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((rootsVarietySpecification == null) ? 0 : rootsVarietySpecification.hashCode());
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
		RootsGardenbed other = (RootsGardenbed) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (rootsVarietySpecification == null) {
			if (other.rootsVarietySpecification != null)
				return false;
		} else if (!rootsVarietySpecification.equals(other.rootsVarietySpecification))
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
