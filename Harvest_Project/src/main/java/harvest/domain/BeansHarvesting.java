package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beans")
public class BeansHarvesting extends HarvestingQuantityDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BeansVariety variety;

	public BeansHarvesting() {
		super();
	}

	public BeansHarvesting(Integer id, LocalDate date, BeansVariety variety, Integer quantity, Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public BeansHarvesting(LocalDate date, BeansVariety variety, Integer quantity, Integer weight) {
		super(date, quantity, weight);
		this.variety = variety;
	}

	public BeansVariety getVariety() {
		return variety;
	}

	public void setVariety(BeansVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}
}
