package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class SquashHarvesting extends Harvesting {

	private Set<SquashVariety> variety;

	public SquashHarvesting() {
		super();
	}

	public SquashHarvesting(Integer id, LocalDate date, Set<SquashVariety> variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public Set<SquashVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<SquashVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}

}
