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
@Table(name = "corn_variety")
public class CornVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((corn == null) ? 0 : corn.hashCode());
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
		CornVariety other = (CornVariety) obj;
		if (corn == null) {
			if (other.corn != null)
				return false;
		} else if (!corn.equals(other.corn))
			return false;
		return true;
	}
}
