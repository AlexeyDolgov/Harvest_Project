package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "berries")
public class BerriesHarvesting extends Harvesting {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BerriesVariety variety;

	public BerriesHarvesting() {
		super();
	}

	public BerriesHarvesting(Integer id, LocalDate date, BerriesVariety variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public BerriesHarvesting(LocalDate date, BerriesVariety variety, Integer weight) {
		super(date, weight);
		this.variety = variety;
	}

	public BerriesVariety getVariety() {
		return variety;
	}

	public void setVariety(BerriesVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}
}
