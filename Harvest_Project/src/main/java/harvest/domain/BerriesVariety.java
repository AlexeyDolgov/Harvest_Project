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
@Table(name = "berries_variety")
public class BerriesVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BerriesHarvesting> berries;

	public BerriesVariety() {
		super();
	}

	public BerriesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public BerriesVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<BerriesHarvesting> getBerries() {
		return berries;
	}

	public void setBerries(Set<BerriesHarvesting> berries) {
		this.berries = berries;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((berries == null) ? 0 : berries.hashCode());
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
		BerriesVariety other = (BerriesVariety) obj;
		if (berries == null) {
			if (other.berries != null)
				return false;
		} else if (!berries.equals(other.berries))
			return false;
		return true;
	}
}
