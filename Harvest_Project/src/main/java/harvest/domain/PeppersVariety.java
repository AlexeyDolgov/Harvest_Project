package harvest.domain;

import java.util.Set;

public class PeppersVariety extends Variety {

	public PeppersVariety() {
		super();
	}

	public PeppersVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
