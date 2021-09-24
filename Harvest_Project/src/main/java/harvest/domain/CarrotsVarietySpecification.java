package harvest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrots_variety_specification")
public class CarrotsVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CarrotsVariety carrotsVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "carrotsVarietySpecification")
	@Column(nullable = false)
	private Set<CarrotsGardenbed> carrotsGardenbeds;

	public CarrotsVarietySpecification() {
	}

	public CarrotsVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public CarrotsVarietySpecification(String customId) {
		super(customId);
	}

	public CarrotsVarietySpecification(Integer id, String customId, Set<CarrotsGardenbed> carrotsGardenbeds) {
		super(id, customId);
		this.carrotsGardenbeds = carrotsGardenbeds;
	}

	public CarrotsVarietySpecification(String customId, Set<CarrotsGardenbed> carrotsGardenbeds) {
		super(customId);
		this.carrotsGardenbeds = carrotsGardenbeds;
	}

	public CarrotsVariety getCarrotsVariety() {
		return carrotsVariety;
	}

	public void setCarrotsVariety(CarrotsVariety carrotsVariety) {
		this.carrotsVariety = carrotsVariety;
	}

	public Set<CarrotsGardenbed> getCarrotsGardenbeds() {
		return carrotsGardenbeds;
	}

	public void setCarrotsGardenbeds(Set<CarrotsGardenbed> carrotsGardenbeds) {
		this.carrotsGardenbeds = carrotsGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((carrotsGardenbeds == null) ? 0 : carrotsGardenbeds.hashCode());
		result = prime * result + ((carrotsVariety == null) ? 0 : carrotsVariety.hashCode());
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
		CarrotsVarietySpecification other = (CarrotsVarietySpecification) obj;
		if (carrotsGardenbeds == null) {
			if (other.carrotsGardenbeds != null)
				return false;
		} else if (!carrotsGardenbeds.equals(other.carrotsGardenbeds))
			return false;
		if (carrotsVariety == null) {
			if (other.carrotsVariety != null)
				return false;
		} else if (!carrotsVariety.equals(other.carrotsVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", CarrotsGardenbeds=" + carrotsGardenbeds;
	}
}
