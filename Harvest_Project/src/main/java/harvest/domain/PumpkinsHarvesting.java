package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pumpkins")
public class PumpkinsHarvesting extends Harvesting {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private PumpkinsVariety variety;

	public PumpkinsHarvesting() {
		super();
	}

	public PumpkinsHarvesting(Integer id, LocalDate date, PumpkinsVariety variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public PumpkinsHarvesting(LocalDate date, PumpkinsVariety variety, Integer weight) {
		super(date, weight);
		this.variety = variety;
	}

	public PumpkinsVariety getVariety() {
		return variety;
	}

	public void setVariety(PumpkinsVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}
}
