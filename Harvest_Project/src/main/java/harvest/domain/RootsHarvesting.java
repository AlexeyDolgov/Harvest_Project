package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class RootsHarvesting extends HarvestingSizeDetermined {

	private Set<RootsVariety> variety;

	public RootsHarvesting() {
		super();
	}

	public RootsHarvesting(Integer id, LocalDate date, Set<RootsVariety> variety, Integer quantity,
			Integer weight, Set<Size> size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public Set<RootsVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<RootsVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}

}
