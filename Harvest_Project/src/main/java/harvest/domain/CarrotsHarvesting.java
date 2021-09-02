package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

public class CarrotsHarvesting extends HarvestingSizeDetermined {

	private Set<CarrotsVariety> variety;

	public CarrotsHarvesting() {
		super();
	}

	public CarrotsHarvesting(Integer id, LocalDate date, Set<CarrotsVariety> variety, Integer quantity,
			Integer weight, Set<Size> size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public Set<CarrotsVariety> getVariety() {
		return variety;
	}

	public void setVariety(Set<CarrotsVariety> variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}

}
