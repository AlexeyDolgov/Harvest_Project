package harvest.domain;

import java.util.Set;

public class CarrotsVariety extends Variety {

	public CarrotsVariety() {
		super();
	}

	public CarrotsVariety(Integer id, String name, Set<Place> place) {
		super(id, name, place);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName() + ", Place=" + getPlace();
	}

}
