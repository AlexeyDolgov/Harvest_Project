package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class GarlicHarvesting extends HarvestingQuantityDetermined {

	private Set<GarlicVariety> variety;

	public GarlicHarvesting() {
		super();
	}

	public GarlicHarvesting(Integer id, LocalDate date, Set<GarlicVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<GarlicVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<GarlicVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
