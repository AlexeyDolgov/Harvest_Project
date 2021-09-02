package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class CabbageHarvesting extends HarvestingQuantityDetermined {

	private Set<CabbageVariety> variety;

	public CabbageHarvesting() {
		super();
	}

	public CabbageHarvesting(Integer id, LocalDate date, Set<CabbageVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<CabbageVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<CabbageVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
