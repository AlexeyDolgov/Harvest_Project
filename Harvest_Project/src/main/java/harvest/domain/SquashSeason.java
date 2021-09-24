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
@Table(name = "squash_season")
public class SquashSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "squash_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<SquashVariety, SquashVarietySpecification> squashSeasonVarieties;
    
	public SquashSeason() {
		super();
	}

	public SquashSeason(Integer id, Integer year) {
		super(id, year);
	}

	public SquashSeason(Integer year) {
		super(year);
	}

	public SquashSeason(Integer id, Integer year,
			Map<SquashVariety, SquashVarietySpecification> squashSeasonVarieties) {
		super(id, year);
		this.squashSeasonVarieties = squashSeasonVarieties;
	}

	public SquashSeason(Integer year, Map<SquashVariety, SquashVarietySpecification> squashSeasonVarieties) {
		super(year);
		this.squashSeasonVarieties = squashSeasonVarieties;
	}

	public Map<SquashVariety, SquashVarietySpecification> getSquashSeasonVarieties() {
		return squashSeasonVarieties;
	}

	public void setSquashSeasonVarieties(Map<SquashVariety, SquashVarietySpecification> squashSeasonVarieties) {
		this.squashSeasonVarieties = squashSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((squashSeasonVarieties == null) ? 0 : squashSeasonVarieties.hashCode());
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
		SquashSeason other = (SquashSeason) obj;
		if (squashSeasonVarieties == null) {
			if (other.squashSeasonVarieties != null)
				return false;
		} else if (!squashSeasonVarieties.equals(other.squashSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", SquashSeasonVarieties=" + squashSeasonVarieties;
	}
}
