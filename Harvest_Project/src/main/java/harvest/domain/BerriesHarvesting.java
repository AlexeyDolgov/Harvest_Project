package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class BerriesHarvesting extends Harvesting {

	private Set<BerriesVariety> variety;

	public BerriesHarvesting() {
		super();
	}

	public BerriesHarvesting(Integer id, LocalDate date, Set<BerriesVariety> variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public Set<BerriesVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<BerriesVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}

}
