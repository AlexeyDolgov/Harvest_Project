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
@Table(name = "peppers_season")
public class PeppersSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "peppers_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<PeppersVariety, PeppersVarietySpecification> peppersSeasonVarieties;
    
	public PeppersSeason() {
		super();
	}

	public PeppersSeason(Integer id, Integer year) {
		super(id, year);
	}

	public PeppersSeason(Integer year) {
		super(year);
	}

	public PeppersSeason(Integer id, Integer year,
			Map<PeppersVariety, PeppersVarietySpecification> peppersSeasonVarieties) {
		super(id, year);
		this.peppersSeasonVarieties = peppersSeasonVarieties;
	}

	public PeppersSeason(Integer year, Map<PeppersVariety, PeppersVarietySpecification> peppersSeasonVarieties) {
		super(year);
		this.peppersSeasonVarieties = peppersSeasonVarieties;
	}

	public Map<PeppersVariety, PeppersVarietySpecification> getPeppersSeasonVarieties() {
		return peppersSeasonVarieties;
	}

	public void setPeppersSeasonVarieties(Map<PeppersVariety, PeppersVarietySpecification> peppersSeasonVarieties) {
		this.peppersSeasonVarieties = peppersSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((peppersSeasonVarieties == null) ? 0 : peppersSeasonVarieties.hashCode());
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
		PeppersSeason other = (PeppersSeason) obj;
		if (peppersSeasonVarieties == null) {
			if (other.peppersSeasonVarieties != null)
				return false;
		} else if (!peppersSeasonVarieties.equals(other.peppersSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", PeppersSeasonVarieties=" + peppersSeasonVarieties;
	}
}
