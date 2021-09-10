package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "berries_variety")
public class BerriesVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BerriesHarvesting> berries;

	public BerriesVariety() {
		super();
	}

	public BerriesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public BerriesVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<BerriesHarvesting> getBerries() {
		return berries;
	}

	public void setBerries(Set<BerriesHarvesting> berries) {
		this.berries = berries;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
