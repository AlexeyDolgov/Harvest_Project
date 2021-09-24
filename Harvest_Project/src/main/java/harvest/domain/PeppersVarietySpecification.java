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
@Table(name = "peppers_variety_specification")
public class PeppersVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private PeppersVariety peppersVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "peppersVarietySpecification")
	@Column(nullable = false)
	private Set<PeppersGardenbed> peppersGardenbeds;

	public PeppersVarietySpecification() {
	}

	public PeppersVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public PeppersVarietySpecification(String customId) {
		super(customId);
	}

	public PeppersVarietySpecification(Integer id, String customId, Set<PeppersGardenbed> peppersGardenbeds) {
		super(id, customId);
		this.peppersGardenbeds = peppersGardenbeds;
	}

	public PeppersVarietySpecification(String customId, Set<PeppersGardenbed> peppersGardenbeds) {
		super(customId);
		this.peppersGardenbeds = peppersGardenbeds;
	}

	public PeppersVariety getPeppersVariety() {
		return peppersVariety;
	}

	public void setPeppersVariety(PeppersVariety peppersVariety) {
		this.peppersVariety = peppersVariety;
	}

	public Set<PeppersGardenbed> getPeppersGardenbeds() {
		return peppersGardenbeds;
	}

	public void setPeppersGardenbeds(Set<PeppersGardenbed> peppersGardenbeds) {
		this.peppersGardenbeds = peppersGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((peppersGardenbeds == null) ? 0 : peppersGardenbeds.hashCode());
		result = prime * result + ((peppersVariety == null) ? 0 : peppersVariety.hashCode());
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
		PeppersVarietySpecification other = (PeppersVarietySpecification) obj;
		if (peppersGardenbeds == null) {
			if (other.peppersGardenbeds != null)
				return false;
		} else if (!peppersGardenbeds.equals(other.peppersGardenbeds))
			return false;
		if (peppersVariety == null) {
			if (other.peppersVariety != null)
				return false;
		} else if (!peppersVariety.equals(other.peppersVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", PeppersGardenbeds=" + peppersGardenbeds;
	}
}
