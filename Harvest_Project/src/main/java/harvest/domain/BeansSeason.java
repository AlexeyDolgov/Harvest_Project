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
@Table(name = "beans_season")
public class BeansSeason extends Season implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "beans_season_varieties", 
      joinColumns = {@JoinColumn(name = "season_id", referencedColumnName = "season_id")},
      inverseJoinColumns = {@JoinColumn(name = "variety_specification_id", referencedColumnName = "variety_specification_id")})
    @MapKeyJoinColumn(name = "variety_id")
    @Column(nullable = false)
    private Map<BeansVariety, BeansVarietySpecification> beansSeasonVarieties;
    
	public BeansSeason() {
		super();
	}

	public BeansSeason(Integer id, Integer year) {
		super(id, year);
	}

	public BeansSeason(Integer year) {
		super(year);
	}

	public BeansSeason(Integer id, Integer year,
			Map<BeansVariety, BeansVarietySpecification> beansSeasonVarieties) {
		super(id, year);
		this.beansSeasonVarieties = beansSeasonVarieties;
	}

	public BeansSeason(Integer year, Map<BeansVariety, BeansVarietySpecification> beansSeasonVarieties) {
		super(year);
		this.beansSeasonVarieties = beansSeasonVarieties;
	}

	public Map<BeansVariety, BeansVarietySpecification> getBeansSeasonVarieties() {
		return beansSeasonVarieties;
	}

	public void setBeansSeasonVarieties(Map<BeansVariety, BeansVarietySpecification> beansSeasonVarieties) {
		this.beansSeasonVarieties = beansSeasonVarieties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beansSeasonVarieties == null) ? 0 : beansSeasonVarieties.hashCode());
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
		BeansSeason other = (BeansSeason) obj;
		if (beansSeasonVarieties == null) {
			if (other.beansSeasonVarieties != null)
				return false;
		} else if (!beansSeasonVarieties.equals(other.beansSeasonVarieties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Year=" + getYear() + ", BeansSeasonVarieties=" + beansSeasonVarieties;
	}
}
