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
@Table(name = "roots_variety")
public class RootsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roots == null) ? 0 : roots.hashCode());
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
		RootsVariety other = (RootsVariety) obj;
		if (roots == null) {
			if (other.roots != null)
				return false;
		} else if (!roots.equals(other.roots))
			return false;
		return true;
	}
}
