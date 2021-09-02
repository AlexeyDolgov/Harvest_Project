package harvest.domain;

import java.util.Set;

public class SquashVariety extends Variety {

	public SquashVariety() {
		super();
	}

	public SquashVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
