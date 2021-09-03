package harvest.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "beans_variety")
public class BeansVariety extends Variety {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BeansHarvesting> beans;

	public BeansVariety() {
		super();
	}

	public BeansVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	public BeansVariety(String name, Set<Place> place) {
		super(name, place);
	}

	public Set<BeansHarvesting> getBeans() {
		return beans;
	}

	public void setBeans(Set<BeansHarvesting> beans) {
		this.beans = beans;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}
}
