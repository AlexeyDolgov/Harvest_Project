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
@Table(name = "peppers_variety")
public class PeppersVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((peppers == null) ? 0 : peppers.hashCode());
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
		PeppersVariety other = (PeppersVariety) obj;
		if (peppers == null) {
			if (other.peppers != null)
				return false;
		} else if (!peppers.equals(other.peppers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
