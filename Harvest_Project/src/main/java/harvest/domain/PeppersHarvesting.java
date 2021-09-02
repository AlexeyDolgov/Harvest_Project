package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class PeppersHarvesting extends HarvestingQuantityDetermined {

	private Set<PeppersVariety> variety;

	public PeppersHarvesting() {
		super();
	}

	public PeppersHarvesting(Integer id, LocalDate date, Set<PeppersVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<PeppersVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<PeppersVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
