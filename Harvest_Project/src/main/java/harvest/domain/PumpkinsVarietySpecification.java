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
@Table(name = "pumpkins_variety_specification")
public class PumpkinsVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private PumpkinsVariety pumpkinsVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pumpkinsVarietySpecification")
	@Column(nullable = false)
	private Set<PumpkinsGardenbed> pumpkinsGardenbeds;

	public PumpkinsVarietySpecification() {
	}

	public PumpkinsVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public PumpkinsVarietySpecification(String customId) {
		super(customId);
	}

	public PumpkinsVarietySpecification(Integer id, String customId, Set<PumpkinsGardenbed> pumpkinsGardenbeds) {
		super(id, customId);
		this.pumpkinsGardenbeds = pumpkinsGardenbeds;
	}

	public PumpkinsVarietySpecification(String customId, Set<PumpkinsGardenbed> pumpkinsGardenbeds) {
		super(customId);
		this.pumpkinsGardenbeds = pumpkinsGardenbeds;
	}

	public PumpkinsVariety getPumpkinsVariety() {
		return pumpkinsVariety;
	}

	public void setPumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		this.pumpkinsVariety = pumpkinsVariety;
	}

	public Set<PumpkinsGardenbed> getPumpkinsGardenbeds() {
		return pumpkinsGardenbeds;
	}

	public void setPumpkinsGardenbeds(Set<PumpkinsGardenbed> pumpkinsGardenbeds) {
		this.pumpkinsGardenbeds = pumpkinsGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pumpkinsGardenbeds == null) ? 0 : pumpkinsGardenbeds.hashCode());
		result = prime * result + ((pumpkinsVariety == null) ? 0 : pumpkinsVariety.hashCode());
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
		PumpkinsVarietySpecification other = (PumpkinsVarietySpecification) obj;
		if (pumpkinsGardenbeds == null) {
			if (other.pumpkinsGardenbeds != null)
				return false;
		} else if (!pumpkinsGardenbeds.equals(other.pumpkinsGardenbeds))
			return false;
		if (pumpkinsVariety == null) {
			if (other.pumpkinsVariety != null)
				return false;
		} else if (!pumpkinsVariety.equals(other.pumpkinsVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", PumpkinsGardenbeds=" + pumpkinsGardenbeds;
	}
}
