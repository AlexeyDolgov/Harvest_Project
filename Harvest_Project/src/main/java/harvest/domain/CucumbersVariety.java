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
@Table(name = "cucumbers_variety")
public class CucumbersVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cucumbers == null) ? 0 : cucumbers.hashCode());
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
		CucumbersVariety other = (CucumbersVariety) obj;
		if (cucumbers == null) {
			if (other.cucumbers != null)
				return false;
		} else if (!cucumbers.equals(other.cucumbers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
