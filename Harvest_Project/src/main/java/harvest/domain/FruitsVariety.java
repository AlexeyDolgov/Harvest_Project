package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fruits_variety")
public class FruitsVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<FruitsHarvesting> fruits;

	public FruitsVariety() {
		super();
	}

	public FruitsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public FruitsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<FruitsHarvesting> getFruits() {
		return fruits;
	}

	public void setFruits(Set<FruitsHarvesting> fruits) {
		this.fruits = fruits;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
