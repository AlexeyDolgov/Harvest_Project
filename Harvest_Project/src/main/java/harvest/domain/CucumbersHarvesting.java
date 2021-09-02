package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class CucumbersHarvesting extends HarvestingQuantityDetermined {

	private Set<CucumbersVariety> variety;

	public CucumbersHarvesting() {
		super();
	}

	public CucumbersHarvesting(Integer id, LocalDate date, Set<CucumbersVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<CucumbersVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<CucumbersVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
