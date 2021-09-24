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
@Table(name = "garlic_variety_specification")
public class GarlicVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private GarlicVariety garlicVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "garlicVarietySpecification")
	@Column(nullable = false)
	private Set<GarlicGardenbed> garlicGardenbeds;

	public GarlicVarietySpecification() {
	}

	public GarlicVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public GarlicVarietySpecification(String customId) {
		super(customId);
	}

	public GarlicVarietySpecification(Integer id, String customId, Set<GarlicGardenbed> garlicGardenbeds) {
		super(id, customId);
		this.garlicGardenbeds = garlicGardenbeds;
	}

	public GarlicVarietySpecification(String customId, Set<GarlicGardenbed> garlicGardenbeds) {
		super(customId);
		this.garlicGardenbeds = garlicGardenbeds;
	}

	public GarlicVariety getGarlicVariety() {
		return garlicVariety;
	}

	public void setGarlicVariety(GarlicVariety garlicVariety) {
		this.garlicVariety = garlicVariety;
	}

	public Set<GarlicGardenbed> getGarlicGardenbeds() {
		return garlicGardenbeds;
	}

	public void setGarlicGardenbeds(Set<GarlicGardenbed> garlicGardenbeds) {
		this.garlicGardenbeds = garlicGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((garlicGardenbeds == null) ? 0 : garlicGardenbeds.hashCode());
		result = prime * result + ((garlicVariety == null) ? 0 : garlicVariety.hashCode());
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
		GarlicVarietySpecification other = (GarlicVarietySpecification) obj;
		if (garlicGardenbeds == null) {
			if (other.garlicGardenbeds != null)
				return false;
		} else if (!garlicGardenbeds.equals(other.garlicGardenbeds))
			return false;
		if (garlicVariety == null) {
			if (other.garlicVariety != null)
				return false;
		} else if (!garlicVariety.equals(other.garlicVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", GarlicGardenbeds=" + garlicGardenbeds;
	}
}
