package harvest.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class HarvestingSizeDetermined extends HarvestingQuantityDetermined {

	@Column
	@Enumerated(EnumType.STRING)
	private Size size;

	public HarvestingSizeDetermined() {
		super();
	}

	public HarvestingSizeDetermined(Integer id, LocalDate date, Integer quantity, Integer weight, Size size) {
		super(id, date, quantity, weight);
		this.size = size;
	}

	public HarvestingSizeDetermined(LocalDate date, Integer quantity, Integer weight, Size size) {
		super(date, quantity, weight);
		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
