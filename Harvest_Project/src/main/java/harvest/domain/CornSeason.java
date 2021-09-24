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
@Table(name = "corn_season")
public class CornSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "corn_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<CornVariety, CornVarietySpecification> cornSeasonVarieties;
    
	public CornSeason() {
		super();
	}

	public CornSeason(Integer id, Integer year) {
		super(id, year);
	}

	public CornSeason(Integer year) {
		super(year);
	}

	public CornSeason(Integer id, Integer year,
			Map<CornVariety, CornVarietySpecification> cornSeasonVarieties) {
		super(id, year);
		this.cornSeasonVarieties = cornSeasonVarieties;
	}

	public CornSeason(Integer year, Map<CornVariety, CornVarietySpecification> cornSeasonVarieties) {
		super(year);
		this.cornSeasonVarieties = cornSeasonVarieties;
	}

	public Map<CornVariety, CornVarietySpecification> getCornSeasonVarieties() {
		return cornSeasonVarieties;
	}

	public void setCornSeasonVarieties(Map<CornVariety, CornVarietySpecification> cornSeasonVarieties) {
		this.cornSeasonVarieties = cornSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cornSeasonVarieties == null) ? 0 : cornSeasonVarieties.hashCode());
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
		CornSeason other = (CornSeason) obj;
		if (cornSeasonVarieties == null) {
			if (other.cornSeasonVarieties != null)
				return false;
		} else if (!cornSeasonVarieties.equals(other.cornSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", CornSeasonVarieties=" + cornSeasonVarieties;
	}
}
