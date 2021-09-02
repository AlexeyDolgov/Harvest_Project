package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class FruitsHarvesting extends Harvesting {

	private Set<FruitsVariety> variety;

	public FruitsHarvesting() {
		super();
	}

	public FruitsHarvesting(Integer id, LocalDate date, Set<FruitsVariety> variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public Set<FruitsVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<FruitsVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}

}
