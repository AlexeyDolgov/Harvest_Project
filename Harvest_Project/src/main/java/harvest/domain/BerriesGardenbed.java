package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "berries_gardenbed")
public class BerriesGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private BerriesVarietySpecification berriesVarietySpecification;

	public BerriesGardenbed() {
		super();
	}

	public BerriesGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public BerriesGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public BerriesGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public BerriesGardenbed(Place place) {
		super(place);
	}

	public BerriesVarietySpecification getBerriesVarietySpecification() {
		return berriesVarietySpecification;
	}

	public void setBerriesVarietySpecification(BerriesVarietySpecification berriesVarietySpecification) {
		this.berriesVarietySpecification = berriesVarietySpecification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((berriesVarietySpecification == null) ? 0 : berriesVarietySpecification.hashCode());
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
		BerriesGardenbed other = (BerriesGardenbed) obj;
		if (berriesVarietySpecification == null) {
			if (other.berriesVarietySpecification != null)
				return false;
		} else if (!berriesVarietySpecification.equals(other.berriesVarietySpecification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Comment=" + getComment() + ", Place=" + getPlace();
	}
}
