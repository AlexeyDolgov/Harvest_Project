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
@Table(name = "cabbage_season")
public class CabbageSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cabbage_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<CabbageVariety, CabbageVarietySpecification> cabbageSeasonVarieties;
    
	public CabbageSeason() {
		super();
	}

	public CabbageSeason(Integer id, Integer year) {
		super(id, year);
	}

	public CabbageSeason(Integer year) {
		super(year);
	}

	public CabbageSeason(Integer id, Integer year,
			Map<CabbageVariety, CabbageVarietySpecification> cabbageSeasonVarieties) {
		super(id, year);
		this.cabbageSeasonVarieties = cabbageSeasonVarieties;
	}

	public CabbageSeason(Integer year, Map<CabbageVariety, CabbageVarietySpecification> cabbageSeasonVarieties) {
		super(year);
		this.cabbageSeasonVarieties = cabbageSeasonVarieties;
	}

	public Map<CabbageVariety, CabbageVarietySpecification> getCabbageSeasonVarieties() {
		return cabbageSeasonVarieties;
	}

	public void setCabbageSeasonVarieties(Map<CabbageVariety, CabbageVarietySpecification> cabbageSeasonVarieties) {
		this.cabbageSeasonVarieties = cabbageSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cabbageSeasonVarieties == null) ? 0 : cabbageSeasonVarieties.hashCode());
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
		CabbageSeason other = (CabbageSeason) obj;
		if (cabbageSeasonVarieties == null) {
			if (other.cabbageSeasonVarieties != null)
				return false;
		} else if (!cabbageSeasonVarieties.equals(other.cabbageSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", CabbageSeasonVarieties=" + cabbageSeasonVarieties;
	}
}
