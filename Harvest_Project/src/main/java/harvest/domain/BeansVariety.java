package harvest.domain;

import java.util.Set;

public class BeansVariety extends Variety {

	public BeansVariety() {
		super();
	}

	public BeansVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);	
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
