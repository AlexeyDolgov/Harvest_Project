package harvest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cucumbers")
public class CucumbersHarvesting extends HarvestingQuantityDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CucumbersVariety variety;

	public CucumbersHarvesting() {
		super();
	}

	public CucumbersHarvesting(Integer id, HarvestingDate date, CucumbersVariety variety, Integer quantity, Integer weight) {
		super(id, date, quantity, weight);
		this.variety = variety;
	}

	public CucumbersHarvesting(HarvestingDate date, CucumbersVariety variety, Integer quantity, Integer weight) {
		super(date, quantity, weight);
		this.variety = variety;
	}

	public CucumbersVariety getVariety() {
		return variety;
	}

	public void setVariety(CucumbersVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight();
	}
}
