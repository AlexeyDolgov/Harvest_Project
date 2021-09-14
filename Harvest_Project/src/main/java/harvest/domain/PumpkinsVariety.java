package harvest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pumpkins_variety")
public class PumpkinsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pumpkins == null) ? 0 : pumpkins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PumpkinsVariety other = (PumpkinsVariety) obj;
		if (pumpkins == null) {
			if (other.pumpkins != null)
				return false;
		} else if (!pumpkins.equals(other.pumpkins))
			return false;
		return true;
	}
}
