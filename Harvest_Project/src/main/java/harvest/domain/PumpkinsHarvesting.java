package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class PumpkinsHarvesting extends Harvesting {

	private Set<PumpkinsVariety> variety;

	public PumpkinsHarvesting() {
		super();
	}

	public PumpkinsHarvesting(Integer id, LocalDate date, Set<PumpkinsVariety> variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public Set<PumpkinsVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<PumpkinsVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}

}
