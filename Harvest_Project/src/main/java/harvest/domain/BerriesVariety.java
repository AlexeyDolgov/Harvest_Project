package harvest.domain;

import java.util.Set;

public class BerriesVariety extends Variety {

	public BerriesVariety() {
		super();
	}

	public BerriesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
