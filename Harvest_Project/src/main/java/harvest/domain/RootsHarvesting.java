package harvest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roots")
public class RootsHarvesting extends HarvestingSizeDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private RootsVariety variety;

	public RootsHarvesting() {
		super();
	}

	public RootsHarvesting(Integer id, HarvestingDate date, RootsVariety variety, Integer quantity, Integer weight,
			Size size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public RootsHarvesting(HarvestingDate date, RootsVariety variety, Integer quantity, Integer weight, Size size) {
		super(date, quantity, weight, size);
		this.variety = variety;
	}

	public RootsVariety getVariety() {
		return variety;
	}

	public void setVariety(RootsVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}
}
