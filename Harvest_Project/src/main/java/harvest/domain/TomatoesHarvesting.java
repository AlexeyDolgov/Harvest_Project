package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class TomatoesHarvesting extends HarvestingQuantityDetermined {
	
	private Set<TomatoesVariety> variety;

	public TomatoesHarvesting() {
		super();
	}

	public TomatoesHarvesting(Integer id, LocalDate date, Set<TomatoesVariety> variety, Integer quantity, Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}
	
	public Set<TomatoesVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<TomatoesVariety> variety) {
		this.variety = variety;		
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}

}
