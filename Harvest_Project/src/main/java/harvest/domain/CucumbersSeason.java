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
@Table(name = "cucumbers_season")
public class CucumbersSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cucumbers_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<CucumbersVariety, CucumbersVarietySpecification> cucumbersSeasonVarieties;
    
	public CucumbersSeason() {
		super();
	}

	public CucumbersSeason(Integer id, Integer year) {
		super(id, year);
	}

	public CucumbersSeason(Integer year) {
		super(year);
	}

	public CucumbersSeason(Integer id, Integer year,
			Map<CucumbersVariety, CucumbersVarietySpecification> cucumbersSeasonVarieties) {
		super(id, year);
		this.cucumbersSeasonVarieties = cucumbersSeasonVarieties;
	}

	public CucumbersSeason(Integer year, Map<CucumbersVariety, CucumbersVarietySpecification> cucumbersSeasonVarieties) {
		super(year);
		this.cucumbersSeasonVarieties = cucumbersSeasonVarieties;
	}

	public Map<CucumbersVariety, CucumbersVarietySpecification> getCucumbersSeasonVarieties() {
		return cucumbersSeasonVarieties;
	}

	public void setCucumbersSeasonVarieties(Map<CucumbersVariety, CucumbersVarietySpecification> cucumbersSeasonVarieties) {
		this.cucumbersSeasonVarieties = cucumbersSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cucumbersSeasonVarieties == null) ? 0 : cucumbersSeasonVarieties.hashCode());
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
		CucumbersSeason other = (CucumbersSeason) obj;
		if (cucumbersSeasonVarieties == null) {
			if (other.cucumbersSeasonVarieties != null)
				return false;
		} else if (!cucumbersSeasonVarieties.equals(other.cucumbersSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", CucumbersSeasonVarieties=" + cucumbersSeasonVarieties;
	}
}
