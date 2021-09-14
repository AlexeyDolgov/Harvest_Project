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
@Table(name = "beetroots_variety")
public class BeetrootsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BeetrootsHarvesting> beetroots;

	public BeetrootsVariety() {
		super();
	}

	public BeetrootsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public BeetrootsVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<BeetrootsHarvesting> getBeetroots() {
		return beetroots;
	}

	public void setBeetroots(Set<BeetrootsHarvesting> beetroots) {
		this.beetroots = beetroots;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beetroots == null) ? 0 : beetroots.hashCode());
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
		BeetrootsVariety other = (BeetrootsVariety) obj;
		if (beetroots == null) {
			if (other.beetroots != null)
				return false;
		} else if (!beetroots.equals(other.beetroots))
			return false;
		return true;
	}
}
