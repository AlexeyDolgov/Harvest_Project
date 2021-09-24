package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fruits_gardenbed")
public class FruitsGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private FruitsVarietySpecification fruitsVarietySpecification;

	public FruitsGardenbed() {
		super();
	}

	public FruitsGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public FruitsGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public FruitsGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public FruitsGardenbed(Place place) {
		super(place);
	}

	public FruitsVarietySpecification getFruitsVarietySpecification() {
		return fruitsVarietySpecification;
	}

	public void setFruitsVarietySpecification(FruitsVarietySpecification fruitsVarietySpecification) {
		this.fruitsVarietySpecification = fruitsVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((fruitsVarietySpecification == null) ? 0 : fruitsVarietySpecification.hashCode());
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
		FruitsGardenbed other = (FruitsGardenbed) obj;
		if (fruitsVarietySpecification == null) {
			if (other.fruitsVarietySpecification != null)
				return false;
		} else if (!fruitsVarietySpecification.equals(other.fruitsVarietySpecification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Comment=" + getComment() + ", Place=" + getPlace();
	}
}
