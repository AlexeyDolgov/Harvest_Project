package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrots_variety")
public class CarrotsVariety extends Variety {

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
}
