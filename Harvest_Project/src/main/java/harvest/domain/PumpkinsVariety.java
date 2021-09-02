package harvest.domain;

import java.util.Set;

public class PumpkinsVariety extends Variety {

	public PumpkinsVariety() {
		super();
	}

	public PumpkinsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}


}
