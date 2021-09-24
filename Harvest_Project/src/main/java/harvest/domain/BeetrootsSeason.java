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
@Table(name = "beetroots_season")
public class BeetrootsSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "beetroots_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<BeetrootsVariety, BeetrootsVarietySpecification> beetrootsSeasonVarieties;
    
	public BeetrootsSeason() {
		super();
	}

	public BeetrootsSeason(Integer id, Integer year) {
		super(id, year);
	}

	public BeetrootsSeason(Integer year) {
		super(year);
	}

	public BeetrootsSeason(Integer id, Integer year,
			Map<BeetrootsVariety, BeetrootsVarietySpecification> beetrootsSeasonVarieties) {
		super(id, year);
		this.beetrootsSeasonVarieties = beetrootsSeasonVarieties;
	}

	public BeetrootsSeason(Integer year, Map<BeetrootsVariety, BeetrootsVarietySpecification> beetrootsSeasonVarieties) {
		super(year);
		this.beetrootsSeasonVarieties = beetrootsSeasonVarieties;
	}

	public Map<BeetrootsVariety, BeetrootsVarietySpecification> getBeetrootsSeasonVarieties() {
		return beetrootsSeasonVarieties;
	}

	public void setBeetrootsSeasonVarieties(Map<BeetrootsVariety, BeetrootsVarietySpecification> beetrootsSeasonVarieties) {
		this.beetrootsSeasonVarieties = beetrootsSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beetrootsSeasonVarieties == null) ? 0 : beetrootsSeasonVarieties.hashCode());
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
		BeetrootsSeason other = (BeetrootsSeason) obj;
		if (beetrootsSeasonVarieties == null) {
			if (other.beetrootsSeasonVarieties != null)
				return false;
		} else if (!beetrootsSeasonVarieties.equals(other.beetrootsSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", BeetrootsSeasonVarieties=" + beetrootsSeasonVarieties;
	}
}
