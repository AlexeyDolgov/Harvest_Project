package harvest.domain;

import java.util.Set;

public class CucumbersVariety extends Variety {

	public CucumbersVariety() {
		super();
	}

	public CucumbersVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
