package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "beetroots_variety")
public class BeetrootsVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BeetrootsHarvesting> beetroots;

	public BeetrootsVariety() {
		super();
	}

	public BeetrootsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public BeetrootsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<BeetrootsHarvesting> getBeetroots() {
		return beetroots;
	}

	public void setBeetroots(Set<BeetrootsHarvesting> beetroots) {
		this.beetroots = beetroots;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
