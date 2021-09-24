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
@Table(name = "fruits_variety")
public class FruitsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<FruitsHarvesting> fruits;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private FruitsSeason fruitsSeason;
	
	public FruitsVariety() {
		super();
	}

	public FruitsVariety(Integer id, String name) {
		super(id, name);
	}

	public FruitsVariety(String name) {
		super(name);
	}

	public Set<FruitsHarvesting> getFruits() {
		return fruits;
	}

	public void setFruits(Set<FruitsHarvesting> fruits) {
		this.fruits = fruits;
	}

	public FruitsSeason getFruitsSeason() {
		return fruitsSeason;
	}

	public void setFruitsSeason(FruitsSeason fruitsSeason) {
		this.fruitsSeason = fruitsSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fruits == null) ? 0 : fruits.hashCode());
		result = prime * result + ((fruitsSeason == null) ? 0 : fruitsSeason.hashCode());
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
		FruitsVariety other = (FruitsVariety) obj;
		if (fruits == null) {
			if (other.fruits != null)
				return false;
		} else if (!fruits.equals(other.fruits))
			return false;
		if (fruitsSeason == null) {
			if (other.fruitsSeason != null)
				return false;
		} else if (!fruitsSeason.equals(other.fruitsSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
