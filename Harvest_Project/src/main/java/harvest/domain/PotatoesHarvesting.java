package harvest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "potatoes")
public class PotatoesHarvesting extends HarvestingQuantityDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private PotatoesVariety variety;

	@Column
	@Enumerated(EnumType.STRING)
	private SizeExtended size;

	public PotatoesHarvesting() {
		super();
	}

	public PotatoesHarvesting(Integer id, HarvestingDate date, PotatoesVariety variety, Integer quantity, Integer weight,
			SizeExtended size) {
		super(id, date, quantity, weight);
		this.variety = variety;
		this.size = size;
	}

	public PotatoesHarvesting(HarvestingDate date, PotatoesVariety variety, Integer quantity, Integer weight,
			SizeExtended size) {
		super(date, quantity, weight);
		this.variety = variety;
		this.size = size;
	}

	public PotatoesVariety getVariety() {
		return variety;
	}

	public void setVariety(PotatoesVariety variety) {
		this.variety = variety;
	}

	public SizeExtended getSize() {
		return size;
	}

	public void setSize(SizeExtended size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}
}
