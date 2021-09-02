package harvest.domain;

import java.util.Set;

public class CornVariety extends Variety {

	public CornVariety() {
		super();
	}

	public CornVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
