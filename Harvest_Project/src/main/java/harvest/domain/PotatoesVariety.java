package harvest.domain;

import java.util.Set;

public class PotatoesVariety extends Variety {

	public PotatoesVariety() {
		super();
	}

	public PotatoesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
