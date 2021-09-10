package harvest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class HarvestingQuantityDetermined extends Harvesting {

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private Integer quantity;

	public HarvestingQuantityDetermined() {
		super();
	}

	public HarvestingQuantityDetermined(Integer id, HarvestingDate date, Integer quantity, Integer weight) {
		super(id, date, weight);
		this.quantity = quantity;
	}

	public HarvestingQuantityDetermined(HarvestingDate date, Integer quantity, Integer weight) {
		super(date, weight);
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
