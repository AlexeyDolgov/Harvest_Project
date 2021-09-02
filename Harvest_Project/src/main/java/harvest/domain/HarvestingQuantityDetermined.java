package harvest.domain;

import java.time.LocalDate;

public abstract class HarvestingQuantityDetermined extends Harvesting {

	private Integer quantity;

	public HarvestingQuantityDetermined() {
		super();
	}

	public HarvestingQuantityDetermined(Integer id, LocalDate date, Integer quantity, Integer weight) {
		super(id, date, weight);
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
