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
@Table(name = "potatoes_variety_specification")
public class PotatoesVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private PotatoesVariety potatoesVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "potatoesVarietySpecification")
	@Column(nullable = false)
	private Set<PotatoesGardenbed> potatoesGardenbeds;

	public PotatoesVarietySpecification() {
	}

	public PotatoesVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public PotatoesVarietySpecification(String customId) {
		super(customId);
	}

	public PotatoesVarietySpecification(Integer id, String customId, Set<PotatoesGardenbed> potatoesGardenbeds) {
		super(id, customId);
		this.potatoesGardenbeds = potatoesGardenbeds;
	}

	public PotatoesVarietySpecification(String customId, Set<PotatoesGardenbed> potatoesGardenbeds) {
		super(customId);
		this.potatoesGardenbeds = potatoesGardenbeds;
	}

	public PotatoesVariety getPotatoesVariety() {
		return potatoesVariety;
	}

	public void setPotatoesVariety(PotatoesVariety potatoesVariety) {
		this.potatoesVariety = potatoesVariety;
	}

	public Set<PotatoesGardenbed> getPotatoesGardenbeds() {
		return potatoesGardenbeds;
	}

	public void setPotatoesGardenbeds(Set<PotatoesGardenbed> potatoesGardenbeds) {
		this.potatoesGardenbeds = potatoesGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((potatoesGardenbeds == null) ? 0 : potatoesGardenbeds.hashCode());
		result = prime * result + ((potatoesVariety == null) ? 0 : potatoesVariety.hashCode());
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
		PotatoesVarietySpecification other = (PotatoesVarietySpecification) obj;
		if (potatoesGardenbeds == null) {
			if (other.potatoesGardenbeds != null)
				return false;
		} else if (!potatoesGardenbeds.equals(other.potatoesGardenbeds))
			return false;
		if (potatoesVariety == null) {
			if (other.potatoesVariety != null)
				return false;
		} else if (!potatoesVariety.equals(other.potatoesVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", PotatoesGardenbeds=" + potatoesGardenbeds;
	}
}
