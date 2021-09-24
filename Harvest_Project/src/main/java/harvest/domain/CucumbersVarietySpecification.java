package harvest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cucumbers_variety_specification")
public class CucumbersVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CucumbersVariety cucumbersVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cucumbersVarietySpecification")
	@Column(nullable = false)
	private Set<CucumbersGardenbed> cucumbersGardenbeds;

	public CucumbersVarietySpecification() {
	}

	public CucumbersVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public CucumbersVarietySpecification(String customId) {
		super(customId);
	}

	public CucumbersVarietySpecification(Integer id, String customId, Set<CucumbersGardenbed> cucumbersGardenbeds) {
		super(id, customId);
		this.cucumbersGardenbeds = cucumbersGardenbeds;
	}

	public CucumbersVarietySpecification(String customId, Set<CucumbersGardenbed> cucumbersGardenbeds) {
		super(customId);
		this.cucumbersGardenbeds = cucumbersGardenbeds;
	}

	public CucumbersVariety getCucumbersVariety() {
		return cucumbersVariety;
	}

	public void setCucumbersVariety(CucumbersVariety cucumbersVariety) {
		this.cucumbersVariety = cucumbersVariety;
	}

	public Set<CucumbersGardenbed> getCucumbersGardenbeds() {
		return cucumbersGardenbeds;
	}

	public void setCucumbersGardenbeds(Set<CucumbersGardenbed> cucumbersGardenbeds) {
		this.cucumbersGardenbeds = cucumbersGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cucumbersGardenbeds == null) ? 0 : cucumbersGardenbeds.hashCode());
		result = prime * result + ((cucumbersVariety == null) ? 0 : cucumbersVariety.hashCode());
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
		CucumbersVarietySpecification other = (CucumbersVarietySpecification) obj;
		if (cucumbersGardenbeds == null) {
			if (other.cucumbersGardenbeds != null)
				return false;
		} else if (!cucumbersGardenbeds.equals(other.cucumbersGardenbeds))
			return false;
		if (cucumbersVariety == null) {
			if (other.cucumbersVariety != null)
				return false;
		} else if (!cucumbersVariety.equals(other.cucumbersVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", CucumbersGardenbeds=" + cucumbersGardenbeds;
	}
}
