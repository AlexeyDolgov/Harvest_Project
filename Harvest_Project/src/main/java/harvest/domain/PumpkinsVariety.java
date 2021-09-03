package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pumpkins_variety")
public class PumpkinsVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<PumpkinsHarvesting> pumpkins;

	public PumpkinsVariety() {
		super();
	}

	public PumpkinsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public PumpkinsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<PumpkinsHarvesting> getPumpkins() {
		return pumpkins;
	}

	public void setPumpkins(Set<PumpkinsHarvesting> pumpkins) {
		this.pumpkins = pumpkins;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
