package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roots_variety")
public class RootsVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<RootsHarvesting> roots;

	public RootsVariety() {
		super();
	}

	public RootsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public RootsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<RootsHarvesting> getRoots() {
		return roots;
	}

	public void setRoots(Set<RootsHarvesting> roots) {
		this.roots = roots;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
