package harvest.domain;

import java.util.Set;

public class GarlicVariety extends Variety {

	public GarlicVariety() {
		super();
	}

	public GarlicVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
