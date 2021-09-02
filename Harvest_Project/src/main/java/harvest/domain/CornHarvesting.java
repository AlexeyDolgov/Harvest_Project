package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class CornHarvesting extends HarvestingQuantityDetermined {

	private Set<CornVariety> variety;

	public CornHarvesting() {
		super();
	}

	public CornHarvesting(Integer id, LocalDate date, Set<CornVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<CornVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<CornVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
