package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "peppers_variety")
public class PeppersVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<PeppersHarvesting> peppers;

	public PeppersVariety() {
		super();
	}

	public PeppersVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public PeppersVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<PeppersHarvesting> getPeppers() {
		return peppers;
	}

	public void setPeppers(Set<PeppersHarvesting> peppers) {
		this.peppers = peppers;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
