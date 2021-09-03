package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "garlic")
public class GarlicHarvesting extends HarvestingQuantityDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private GarlicVariety variety;

	public GarlicHarvesting() {
		super();
	}

	public GarlicHarvesting(Integer id, LocalDate date, GarlicVariety variety, Integer quantity, Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public GarlicHarvesting(LocalDate date, GarlicVariety variety, Integer quantity, Integer weight) {
		super(date, quantity, weight);
		this.variety = variety;
	}

	public GarlicVariety getVariety() {
		return variety;
	}

	public void setVariety(GarlicVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}
}
