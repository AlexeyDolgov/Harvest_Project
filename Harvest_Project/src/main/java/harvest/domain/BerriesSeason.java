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
@Table(name = "berries_season")
public class BerriesSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "berries_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<BerriesVariety, BerriesVarietySpecification> berriesSeasonVarieties;
    
	public BerriesSeason() {
		super();
	}

	public BerriesSeason(Integer id, Integer year) {
		super(id, year);
	}

	public BerriesSeason(Integer year) {
		super(year);
	}

	public BerriesSeason(Integer id, Integer year,
			Map<BerriesVariety, BerriesVarietySpecification> berriesSeasonVarieties) {
		super(id, year);
		this.berriesSeasonVarieties = berriesSeasonVarieties;
	}

	public BerriesSeason(Integer year, Map<BerriesVariety, BerriesVarietySpecification> berriesSeasonVarieties) {
		super(year);
		this.berriesSeasonVarieties = berriesSeasonVarieties;
	}

	public Map<BerriesVariety, BerriesVarietySpecification> getBerriesSeasonVarieties() {
		return berriesSeasonVarieties;
	}

	public void setBerriesSeasonVarieties(Map<BerriesVariety, BerriesVarietySpecification> berriesSeasonVarieties) {
		this.berriesSeasonVarieties = berriesSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((berriesSeasonVarieties == null) ? 0 : berriesSeasonVarieties.hashCode());
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
		BerriesSeason other = (BerriesSeason) obj;
		if (berriesSeasonVarieties == null) {
			if (other.berriesSeasonVarieties != null)
				return false;
		} else if (!berriesSeasonVarieties.equals(other.berriesSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", BerriesSeasonVarieties=" + berriesSeasonVarieties;
	}
}
