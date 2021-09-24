package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrots_gardenbed")
public class CarrotsGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Double width;

	@Column(nullable = false)
	private Double length;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private CarrotsVarietySpecification carrotsVarietySpecification;

	public CarrotsGardenbed() {
		super();
	}

	public CarrotsGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public CarrotsGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public CarrotsGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public CarrotsGardenbed(Place place) {
		super(place);
	}

	public CarrotsGardenbed(Integer id, String comment, Place place, Double width,
			Double length) {
		super(id, comment, place);
		this.width = width;
		this.length = length;
	}

	public CarrotsGardenbed(Integer id, Place place, Double width, Double length) {
		super(id, place);
		this.width = width;
		this.length = length;
	}

	public CarrotsGardenbed(String comment, Place place, Double width, Double length) {
		super(comment, place);
		this.width = width;
		this.length = length;
	}

	public CarrotsGardenbed(Place place, Double width, Double length) {
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

	public CarrotsVarietySpecification getCarrotsVarietySpecification() {
		return carrotsVarietySpecification;
	}

	public void setCarrotsVarietySpecification(CarrotsVarietySpecification carrotsVarietySpecification) {
		this.carrotsVarietySpecification = carrotsVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((carrotsVarietySpecification == null) ? 0 : carrotsVarietySpecification.hashCode());
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
		CarrotsGardenbed other = (CarrotsGardenbed) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (carrotsVarietySpecification == null) {
			if (other.carrotsVarietySpecification != null)
				return false;
		} else if (!carrotsVarietySpecification.equals(other.carrotsVarietySpecification))
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
