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
@Table(name = "pumpkins_season")
public class PumpkinsSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pumpkins_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<PumpkinsVariety, PumpkinsVarietySpecification> pumpkinsSeasonVarieties;
    
	public PumpkinsSeason() {
		super();
	}

	public PumpkinsSeason(Integer id, Integer year) {
		super(id, year);
	}

	public PumpkinsSeason(Integer year) {
		super(year);
	}

	public PumpkinsSeason(Integer id, Integer year,
			Map<PumpkinsVariety, PumpkinsVarietySpecification> pumpkinsSeasonVarieties) {
		super(id, year);
		this.pumpkinsSeasonVarieties = pumpkinsSeasonVarieties;
	}

	public PumpkinsSeason(Integer year, Map<PumpkinsVariety, PumpkinsVarietySpecification> pumpkinsSeasonVarieties) {
		super(year);
		this.pumpkinsSeasonVarieties = pumpkinsSeasonVarieties;
	}

	public Map<PumpkinsVariety, PumpkinsVarietySpecification> getPumpkinsSeasonVarieties() {
		return pumpkinsSeasonVarieties;
	}

	public void setPumpkinsSeasonVarieties(Map<PumpkinsVariety, PumpkinsVarietySpecification> pumpkinsSeasonVarieties) {
		this.pumpkinsSeasonVarieties = pumpkinsSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pumpkinsSeasonVarieties == null) ? 0 : pumpkinsSeasonVarieties.hashCode());
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
		PumpkinsSeason other = (PumpkinsSeason) obj;
		if (pumpkinsSeasonVarieties == null) {
			if (other.pumpkinsSeasonVarieties != null)
				return false;
		} else if (!pumpkinsSeasonVarieties.equals(other.pumpkinsSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", PumpkinsSeasonVarieties=" + pumpkinsSeasonVarieties;
	}
}
