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
@Table(name = "potatoes_season")
public class PotatoesSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "potatoes_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<PotatoesVariety, PotatoesVarietySpecification> potatoesSeasonVarieties;
    
	public PotatoesSeason() {
		super();
	}

	public PotatoesSeason(Integer id, Integer year) {
		super(id, year);
	}

	public PotatoesSeason(Integer year) {
		super(year);
	}

	public PotatoesSeason(Integer id, Integer year,
			Map<PotatoesVariety, PotatoesVarietySpecification> potatoesSeasonVarieties) {
		super(id, year);
		this.potatoesSeasonVarieties = potatoesSeasonVarieties;
	}

	public PotatoesSeason(Integer year, Map<PotatoesVariety, PotatoesVarietySpecification> potatoesSeasonVarieties) {
		super(year);
		this.potatoesSeasonVarieties = potatoesSeasonVarieties;
	}

	public Map<PotatoesVariety, PotatoesVarietySpecification> getPotatoesSeasonVarieties() {
		return potatoesSeasonVarieties;
	}

	public void setPotatoesSeasonVarieties(Map<PotatoesVariety, PotatoesVarietySpecification> potatoesSeasonVarieties) {
		this.potatoesSeasonVarieties = potatoesSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((potatoesSeasonVarieties == null) ? 0 : potatoesSeasonVarieties.hashCode());
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
		PotatoesSeason other = (PotatoesSeason) obj;
		if (potatoesSeasonVarieties == null) {
			if (other.potatoesSeasonVarieties != null)
				return false;
		} else if (!potatoesSeasonVarieties.equals(other.potatoesSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", PotatoesSeasonVarieties=" + potatoesSeasonVarieties;
	}
}
