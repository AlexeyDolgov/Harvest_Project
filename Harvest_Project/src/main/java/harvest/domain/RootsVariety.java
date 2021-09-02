package harvest.domain;

import java.util.Set;

public class RootsVariety extends Variety {

	public RootsVariety() {
		super();
	}

	public RootsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
