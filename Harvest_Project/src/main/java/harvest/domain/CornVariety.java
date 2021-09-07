package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "corn_variety")
public class CornVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CornHarvesting> corn;

	public CornVariety() {
		super();
	}

	public CornVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public CornVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<CornHarvesting> getCorn() {
		return corn;
	}

	public void setCorn(Set<CornHarvesting> corn) {
		this.corn = corn;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
