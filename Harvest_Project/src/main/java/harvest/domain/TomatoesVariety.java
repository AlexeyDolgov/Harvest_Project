package harvest.domain;

import java.util.Set;

public class TomatoesVariety extends Variety {

	public TomatoesVariety() {
		super();
	}

	public TomatoesVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
