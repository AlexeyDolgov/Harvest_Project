package harvest.domain;

import java.util.Set;

public class FruitsVariety extends Variety {

	public FruitsVariety() {
		super();
	}

	public FruitsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
