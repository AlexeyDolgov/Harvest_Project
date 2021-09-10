package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "squash_variety")
public class SquashVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<SquashHarvesting> squash;

	public SquashVariety() {
		super();
	}

	public SquashVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public SquashVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<SquashHarvesting> getSquash() {
		return squash;
	}

	public void setSquash(Set<SquashHarvesting> squash) {
		this.squash = squash;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
