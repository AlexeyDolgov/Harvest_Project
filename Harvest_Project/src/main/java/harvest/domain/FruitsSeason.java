package harvest.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fruits_season")
public class FruitsSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "fruits_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<FruitsVariety, FruitsVarietySpecification> fruitsSeasonVarieties;
    
	public FruitsSeason() {
		super();
	}

	public FruitsSeason(Integer id, Integer year) {
		super(id, year);
	}

	public FruitsSeason(Integer year) {
		super(year);
	}

	public FruitsSeason(Integer id, Integer year,
			Map<FruitsVariety, FruitsVarietySpecification> fruitsSeasonVarieties) {
		super(id, year);
		this.fruitsSeasonVarieties = fruitsSeasonVarieties;
	}

	public FruitsSeason(Integer year, Map<FruitsVariety, FruitsVarietySpecification> fruitsSeasonVarieties) {
		super(year);
		this.fruitsSeasonVarieties = fruitsSeasonVarieties;
	}

	public Map<FruitsVariety, FruitsVarietySpecification> getFruitsSeasonVarieties() {
		return fruitsSeasonVarieties;
	}

	public void setFruitsSeasonVarieties(Map<FruitsVariety, FruitsVarietySpecification> fruitsSeasonVarieties) {
		this.fruitsSeasonVarieties = fruitsSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fruitsSeasonVarieties == null) ? 0 : fruitsSeasonVarieties.hashCode());
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
		FruitsSeason other = (FruitsSeason) obj;
		if (fruitsSeasonVarieties == null) {
			if (other.fruitsSeasonVarieties != null)
				return false;
		} else if (!fruitsSeasonVarieties.equals(other.fruitsSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", FruitsSeasonVarieties=" + fruitsSeasonVarieties;
	}
}
