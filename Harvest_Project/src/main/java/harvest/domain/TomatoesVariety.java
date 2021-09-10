package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tomatoes_variety")
public class TomatoesVariety extends Variety {

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
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
