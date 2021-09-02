package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class BeetrootsHarvesting extends HarvestingSizeDetermined {

	private Set<BeetrootsVariety> variety;

	public BeetrootsHarvesting() {
		super();
	}

	public BeetrootsHarvesting(Integer id, LocalDate date, Set<BeetrootsVariety> variety, Integer quantity,
			Integer weight, Set<Size> size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public Set<BeetrootsVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<BeetrootsVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}

}
