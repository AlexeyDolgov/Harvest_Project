package harvest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrots_variety")
public class CarrotsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CarrotsHarvesting> carrots;

	public CarrotsVariety() {
		super();
	}

	public CarrotsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public CarrotsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<CarrotsHarvesting> getCarrots() {
		return carrots;
	}

	public void setCarrots(Set<CarrotsHarvesting> carrots) {
		this.carrots = carrots;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((carrots == null) ? 0 : carrots.hashCode());
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
		CarrotsVariety other = (CarrotsVariety) obj;
		if (carrots == null) {
			if (other.carrots != null)
				return false;
		} else if (!carrots.equals(other.carrots))
			return false;
		return true;
	}
}
