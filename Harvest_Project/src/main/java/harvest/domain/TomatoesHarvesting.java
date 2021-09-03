package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tomatoes")
public class TomatoesHarvesting extends HarvestingQuantityDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private TomatoesVariety variety;

	public TomatoesHarvesting() {
		super();
	}

	public TomatoesHarvesting(Integer id, LocalDate date, TomatoesVariety variety, Integer quantity, Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public TomatoesHarvesting(LocalDate date, TomatoesVariety variety, Integer quantity, Integer weight) {
		super(date, quantity, weight);
		this.variety = variety;
	}

	public TomatoesVariety getVariety() {
		return variety;
	}

	public void setVariety(TomatoesVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}
}
