package harvest.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrots")
public class CarrotsHarvesting extends HarvestingSizeDetermined {

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CarrotsVariety variety;

	public CarrotsHarvesting() {
		super();
	}

	public CarrotsHarvesting(Integer id, LocalDate date, CarrotsVariety variety, Integer quantity, Integer weight,
			Size size) {
		super(id, date, quantity, weight, size);
		this.variety = variety;
	}

	public CarrotsHarvesting(LocalDate date, CarrotsVariety variety, Integer quantity, Integer weight, Size size) {
		super(date, quantity, weight, size);
		this.variety = variety;
	}

	public CarrotsVariety getVariety() {
		return variety;
	}

	public void setVariety(CarrotsVariety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Date=" + getDate() + ", Variety=" + getVariety() + ", Quantity=" + getQuantity()
				+ ", Weight=" + getWeight() + ", Size=" + getSize();
	}
}
