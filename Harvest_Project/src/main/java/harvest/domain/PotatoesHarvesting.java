package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class PotatoesHarvesting extends HarvestingQuantityDetermined {

	private Set<PotatoesVariety> variety;
	private Set<SizeExtended> size;

	public PotatoesHarvesting() {
		super();
	}

	public PotatoesHarvesting(Integer id, LocalDate date, Set<PotatoesVariety> variety, Integer quantity,
			Integer weight, Set<SizeExtended> size) {
		super(id, date, quantity, weight);
		this.variety = variety;
		this.size = size;
	}

	public Set<PotatoesVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<PotatoesVariety> variety) {
		this.variety = variety;
	}

	public Set<SizeExtended> getSize() {
		return size;
	}

	public void setSize(Set<SizeExtended> size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}

}
