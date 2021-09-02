package harvest.domain;

import java.util.Set;

public class CabbageVariety extends Variety {

	public CabbageVariety() {
		super();
	}

	public CabbageVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
