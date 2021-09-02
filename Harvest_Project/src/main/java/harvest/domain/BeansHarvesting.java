package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class BeansHarvesting extends HarvestingQuantityDetermined {

	private Set<BeansVariety> variety;

	public BeansHarvesting() {
		super();
	}

	public BeansHarvesting(Integer id, LocalDate date, Set<BeansVariety> variety, Integer quantity,
			Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public Set<BeansVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<BeansVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
