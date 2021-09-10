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
@Table(name = "tomatoes_variety")
public class TomatoesVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<TomatoesHarvesting> tomatoes;

	public TomatoesVariety() {
		super();
	}

	public TomatoesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public TomatoesVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<TomatoesHarvesting> getTomatoes() {
		return tomatoes;
	}

	public void setTomatoes(Set<TomatoesHarvesting> tomatoes) {
		this.tomatoes = tomatoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tomatoes == null) ? 0 : tomatoes.hashCode());
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
		TomatoesVariety other = (TomatoesVariety) obj;
		if (tomatoes == null) {
			if (other.tomatoes != null)
				return false;
		} else if (!tomatoes.equals(other.tomatoes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
