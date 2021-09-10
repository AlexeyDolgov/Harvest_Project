package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cabbage_variety")
public class CabbageVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CabbageHarvesting> cabbage;

	public CabbageVariety() {
		super();
	}

	public CabbageVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public CabbageVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<CabbageHarvesting> getCabbage() {
		return cabbage;
	}

	public void setCabbage(Set<CabbageHarvesting> cabbage) {
		this.cabbage = cabbage;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
