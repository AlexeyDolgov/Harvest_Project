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
@Table(name = "beetroots_variety_specification")
public class BeetrootsVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BeetrootsVariety beetrootsVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "beetrootsVarietySpecification")
	@Column(nullable = false)
	private Set<BeetrootsGardenbed> beetrootsGardenbeds;

	public BeetrootsVarietySpecification() {
	}

	public BeetrootsVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public BeetrootsVarietySpecification(String customId) {
		super(customId);
	}

	public BeetrootsVarietySpecification(Integer id, String customId, Set<BeetrootsGardenbed> beetrootsGardenbeds) {
		super(id, customId);
		this.beetrootsGardenbeds = beetrootsGardenbeds;
	}

	public BeetrootsVarietySpecification(String customId, Set<BeetrootsGardenbed> beetrootsGardenbeds) {
		super(customId);
		this.beetrootsGardenbeds = beetrootsGardenbeds;
	}

	public BeetrootsVariety getBeetrootsVariety() {
		return beetrootsVariety;
	}

	public void setBeetrootsVariety(BeetrootsVariety beetrootsVariety) {
		this.beetrootsVariety = beetrootsVariety;
	}

	public Set<BeetrootsGardenbed> getBeetrootsGardenbeds() {
		return beetrootsGardenbeds;
	}

	public void setBeetrootsGardenbeds(Set<BeetrootsGardenbed> beetrootsGardenbeds) {
		this.beetrootsGardenbeds = beetrootsGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beetrootsGardenbeds == null) ? 0 : beetrootsGardenbeds.hashCode());
		result = prime * result + ((beetrootsVariety == null) ? 0 : beetrootsVariety.hashCode());
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
		BeetrootsVarietySpecification other = (BeetrootsVarietySpecification) obj;
		if (beetrootsGardenbeds == null) {
			if (other.beetrootsGardenbeds != null)
				return false;
		} else if (!beetrootsGardenbeds.equals(other.beetrootsGardenbeds))
			return false;
		if (beetrootsVariety == null) {
			if (other.beetrootsVariety != null)
				return false;
		} else if (!beetrootsVariety.equals(other.beetrootsVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", BeetrootsGardenbeds=" + beetrootsGardenbeds;
	}
}
