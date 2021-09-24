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
@Table(name = "tomatoes_season")
public class TomatoesSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tomatoes_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<TomatoesVariety, TomatoesVarietySpecification> tomatoesSeasonVarieties;
    
	public TomatoesSeason() {
		super();
	}

	public TomatoesSeason(Integer id, Integer year) {
		super(id, year);
	}

	public TomatoesSeason(Integer year) {
		super(year);
	}

	public TomatoesSeason(Integer id, Integer year,
			Map<TomatoesVariety, TomatoesVarietySpecification> tomatoesSeasonVarieties) {
		super(id, year);
		this.tomatoesSeasonVarieties = tomatoesSeasonVarieties;
	}

	public TomatoesSeason(Integer year, Map<TomatoesVariety, TomatoesVarietySpecification> tomatoesSeasonVarieties) {
		super(year);
		this.tomatoesSeasonVarieties = tomatoesSeasonVarieties;
	}

	public Map<TomatoesVariety, TomatoesVarietySpecification> getTomatoesSeasonVarieties() {
		return tomatoesSeasonVarieties;
	}

	public void setTomatoesSeasonVarieties(Map<TomatoesVariety, TomatoesVarietySpecification> tomatoesSeasonVarieties) {
		this.tomatoesSeasonVarieties = tomatoesSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tomatoesSeasonVarieties == null) ? 0 : tomatoesSeasonVarieties.hashCode());
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
		TomatoesSeason other = (TomatoesSeason) obj;
		if (tomatoesSeasonVarieties == null) {
			if (other.tomatoesSeasonVarieties != null)
				return false;
		} else if (!tomatoesSeasonVarieties.equals(other.tomatoesSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", TomatoesSeasonVarieties=" + tomatoesSeasonVarieties;
	}
}
