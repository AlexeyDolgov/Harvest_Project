package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cucumbers_variety")
public class CucumbersVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CucumbersHarvesting> cucumbers;

	public CucumbersVariety() {
		super();
	}

	public CucumbersVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public CucumbersVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<CucumbersHarvesting> getCucumbers() {
		return cucumbers;
	}

	public void setCucumbers(Set<CucumbersHarvesting> cucumbers) {
		this.cucumbers = cucumbers;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
