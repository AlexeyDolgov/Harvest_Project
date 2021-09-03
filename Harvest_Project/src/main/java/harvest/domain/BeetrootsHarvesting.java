package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beetroots")
public class BeetrootsHarvesting extends HarvestingSizeDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BeetrootsVariety variety;

	public BeetrootsHarvesting() {
		super();
	}

	public BeetrootsHarvesting(Integer id, LocalDate date, BeetrootsVariety variety, Integer quantity, Integer weight,
			Size size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public BeetrootsHarvesting(LocalDate date, BeetrootsVariety variety, Integer quantity, Integer weight, Size size) {
		super(date, quantity, weight, size);
		this.variety = variety;
	}

	public BeetrootsVariety getVariety() {
		return variety;
	}

	public void setVariety(BeetrootsVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}
}
