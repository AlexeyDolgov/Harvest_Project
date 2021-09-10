package harvest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "squash")
public class SquashHarvesting extends Harvesting {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private SquashVariety variety;

	public SquashHarvesting() {
		super();
	}

	public SquashHarvesting(Integer id, HarvestingDate date, SquashVariety variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public SquashHarvesting(HarvestingDate date, SquashVariety variety, Integer weight) {
		super(date, weight);
		this.variety = variety;
	}

	public SquashVariety getVariety() {
		return variety;
	}

	public void setVariety(SquashVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}
}
