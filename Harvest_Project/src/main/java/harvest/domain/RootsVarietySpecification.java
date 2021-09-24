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
@Table(name = "roots_variety_specification")
public class RootsVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private RootsVariety rootsVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rootsVarietySpecification")
	@Column(nullable = false)
	private Set<RootsGardenbed> rootsGardenbeds;

	public RootsVarietySpecification() {
	}

	public RootsVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public RootsVarietySpecification(String customId) {
		super(customId);
	}

	public RootsVarietySpecification(Integer id, String customId, Set<RootsGardenbed> rootsGardenbeds) {
		super(id, customId);
		this.rootsGardenbeds = rootsGardenbeds;
	}

	public RootsVarietySpecification(String customId, Set<RootsGardenbed> rootsGardenbeds) {
		super(customId);
		this.rootsGardenbeds = rootsGardenbeds;
	}

	public RootsVariety getRootsVariety() {
		return rootsVariety;
	}

	public void setRootsVariety(RootsVariety rootsVariety) {
		this.rootsVariety = rootsVariety;
	}

	public Set<RootsGardenbed> getRootsGardenbeds() {
		return rootsGardenbeds;
	}

	public void setRootsGardenbeds(Set<RootsGardenbed> rootsGardenbeds) {
		this.rootsGardenbeds = rootsGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rootsGardenbeds == null) ? 0 : rootsGardenbeds.hashCode());
		result = prime * result + ((rootsVariety == null) ? 0 : rootsVariety.hashCode());
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
		RootsVarietySpecification other = (RootsVarietySpecification) obj;
		if (rootsGardenbeds == null) {
			if (other.rootsGardenbeds != null)
				return false;
		} else if (!rootsGardenbeds.equals(other.rootsGardenbeds))
			return false;
		if (rootsVariety == null) {
			if (other.rootsVariety != null)
				return false;
		} else if (!rootsVariety.equals(other.rootsVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", RootsGardenbeds=" + rootsGardenbeds;
	}
}
