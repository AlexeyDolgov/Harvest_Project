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
@Table(name = "roots_season")
public class RootsSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roots_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<RootsVariety, RootsVarietySpecification> rootsSeasonVarieties;
    
	public RootsSeason() {
		super();
	}

	public RootsSeason(Integer id, Integer year) {
		super(id, year);
	}

	public RootsSeason(Integer year) {
		super(year);
	}

	public RootsSeason(Integer id, Integer year,
			Map<RootsVariety, RootsVarietySpecification> rootsSeasonVarieties) {
		super(id, year);
		this.rootsSeasonVarieties = rootsSeasonVarieties;
	}

	public RootsSeason(Integer year, Map<RootsVariety, RootsVarietySpecification> rootsSeasonVarieties) {
		super(year);
		this.rootsSeasonVarieties = rootsSeasonVarieties;
	}

	public Map<RootsVariety, RootsVarietySpecification> getRootsSeasonVarieties() {
		return rootsSeasonVarieties;
	}

	public void setRootsSeasonVarieties(Map<RootsVariety, RootsVarietySpecification> rootsSeasonVarieties) {
		this.rootsSeasonVarieties = rootsSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rootsSeasonVarieties == null) ? 0 : rootsSeasonVarieties.hashCode());
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
		RootsSeason other = (RootsSeason) obj;
		if (rootsSeasonVarieties == null) {
			if (other.rootsSeasonVarieties != null)
				return false;
		} else if (!rootsSeasonVarieties.equals(other.rootsSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", RootsSeasonVarieties=" + rootsSeasonVarieties;
	}
}
