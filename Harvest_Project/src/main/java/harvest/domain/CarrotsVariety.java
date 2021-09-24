package harvest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrots_variety")
public class CarrotsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CarrotsHarvesting> carrots;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private CarrotsSeason carrotsSeason;
	
	public CarrotsVariety() {
		super();
	}

	public CarrotsVariety(Integer id, String name) {
		super(id, name);
	}

	public CarrotsVariety(String name) {
		super(name);
	}

	public Set<CarrotsHarvesting> getCarrots() {
		return carrots;
	}

	public void setCarrots(Set<CarrotsHarvesting> carrots) {
		this.carrots = carrots;
	}

	public CarrotsSeason getCarrotsSeason() {
		return carrotsSeason;
	}

	public void setCarrotsSeason(CarrotsSeason carrotsSeason) {
		this.carrotsSeason = carrotsSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((carrots == null) ? 0 : carrots.hashCode());
		result = prime * result + ((carrotsSeason == null) ? 0 : carrotsSeason.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrotsVariety other = (CarrotsVariety) obj;
		if (carrots == null) {
			if (other.carrots != null)
				return false;
		} else if (!carrots.equals(other.carrots))
			return false;
		if (carrotsSeason == null) {
			if (other.carrotsSeason != null)
				return false;
		} else if (!carrotsSeason.equals(other.carrotsSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
