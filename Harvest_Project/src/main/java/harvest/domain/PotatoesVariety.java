package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "potatoes_variety")
public class PotatoesVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<PotatoesHarvesting> potatoes;

	public PotatoesVariety() {
		super();
	}

	public PotatoesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public PotatoesVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<PotatoesHarvesting> getPotatoes() {
		return potatoes;
	}

	public void setPotatoes(Set<PotatoesHarvesting> potatoes) {
		this.potatoes = potatoes;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
