package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public abstract class HarvestingSizeDetermined extends HarvestingQuantityDetermined {

	private Set<Size> size;

	public HarvestingSizeDetermined() {
		super();
	}

	public HarvestingSizeDetermined(Integer id, LocalDate date, Integer quantity, Integer weight, Set<Size> size) {
		super(id, date, quantity, weight);
		this.size = size;
	}

	public Set<Size> getSize() {
		return size;
	}

	public void setSize(Set<Size> size) {
		this.size = size;
	}
}
