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
@Table(name = "garlic_season")
public class GarlicSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "garlic_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<GarlicVariety, GarlicVarietySpecification> garlicSeasonVarieties;
    
	public GarlicSeason() {
		super();
	}

	public GarlicSeason(Integer id, Integer year) {
		super(id, year);
	}

	public GarlicSeason(Integer year) {
		super(year);
	}

	public GarlicSeason(Integer id, Integer year,
			Map<GarlicVariety, GarlicVarietySpecification> garlicSeasonVarieties) {
		super(id, year);
		this.garlicSeasonVarieties = garlicSeasonVarieties;
	}

	public GarlicSeason(Integer year, Map<GarlicVariety, GarlicVarietySpecification> garlicSeasonVarieties) {
		super(year);
		this.garlicSeasonVarieties = garlicSeasonVarieties;
	}

	public Map<GarlicVariety, GarlicVarietySpecification> getGarlicSeasonVarieties() {
		return garlicSeasonVarieties;
	}

	public void setGarlicSeasonVarieties(Map<GarlicVariety, GarlicVarietySpecification> garlicSeasonVarieties) {
		this.garlicSeasonVarieties = garlicSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((garlicSeasonVarieties == null) ? 0 : garlicSeasonVarieties.hashCode());
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
		GarlicSeason other = (GarlicSeason) obj;
		if (garlicSeasonVarieties == null) {
			if (other.garlicSeasonVarieties != null)
				return false;
		} else if (!garlicSeasonVarieties.equals(other.garlicSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", GarlicSeasonVarieties=" + garlicSeasonVarieties;
	}
}
