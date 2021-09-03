package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "garlic_variety")
public class GarlicVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<GarlicHarvesting> garlic;

	public GarlicVariety() {
		super();
	}

	public GarlicVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public GarlicVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<GarlicHarvesting> getGarlic() {
		return garlic;
	}

	public void setGarlic(Set<GarlicHarvesting> garlic) {
		this.garlic = garlic;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
