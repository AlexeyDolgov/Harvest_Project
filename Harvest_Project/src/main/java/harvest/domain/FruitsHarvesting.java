package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fruits")
public class FruitsHarvesting extends Harvesting {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private FruitsVariety variety;

	public FruitsHarvesting() {
		super();
	}

	public FruitsHarvesting(Integer id, LocalDate date, FruitsVariety variety, Integer weight) {
		super(id, date, weight);
		this.variety = variety;
	}

	public FruitsHarvesting(LocalDate date, FruitsVariety variety, Integer weight) {
		super(date, weight);
		this.variety = variety;
	}

	public FruitsVariety getVariety() {
		return variety;
	}

	public void setVariety(FruitsVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Weight=" + getWeight();
	}
}
