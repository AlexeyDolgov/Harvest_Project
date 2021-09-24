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
@Table(name = "cabbage_variety_specification")
public class CabbageVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CabbageVariety cabbageVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cabbageVarietySpecification")
	@Column(nullable = false)
	private Set<CabbageGardenbed> cabbageGardenbeds;

	public CabbageVarietySpecification() {
	}

	public CabbageVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public CabbageVarietySpecification(String customId) {
		super(customId);
	}

	public CabbageVarietySpecification(Integer id, String customId, Set<CabbageGardenbed> cabbageGardenbeds) {
		super(id, customId);
		this.cabbageGardenbeds = cabbageGardenbeds;
	}

	public CabbageVarietySpecification(String customId, Set<CabbageGardenbed> cabbageGardenbeds) {
		super(customId);
		this.cabbageGardenbeds = cabbageGardenbeds;
	}

	public CabbageVariety getCabbageVariety() {
		return cabbageVariety;
	}

	public void setCabbageVariety(CabbageVariety cabbageVariety) {
		this.cabbageVariety = cabbageVariety;
	}

	public Set<CabbageGardenbed> getCabbageGardenbeds() {
		return cabbageGardenbeds;
	}

	public void setCabbageGardenbeds(Set<CabbageGardenbed> cabbageGardenbeds) {
		this.cabbageGardenbeds = cabbageGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cabbageGardenbeds == null) ? 0 : cabbageGardenbeds.hashCode());
		result = prime * result + ((cabbageVariety == null) ? 0 : cabbageVariety.hashCode());
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
		CabbageVarietySpecification other = (CabbageVarietySpecification) obj;
		if (cabbageGardenbeds == null) {
			if (other.cabbageGardenbeds != null)
				return false;
		} else if (!cabbageGardenbeds.equals(other.cabbageGardenbeds))
			return false;
		if (cabbageVariety == null) {
			if (other.cabbageVariety != null)
				return false;
		} else if (!cabbageVariety.equals(other.cabbageVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", CabbageGardenbeds=" + cabbageGardenbeds;
	}
}
