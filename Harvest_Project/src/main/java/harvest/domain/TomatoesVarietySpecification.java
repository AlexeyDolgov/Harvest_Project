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
@Table(name = "tomatoes_variety_specification")
public class TomatoesVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private TomatoesVariety tomatoesVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tomatoesVarietySpecification")
	@Column(nullable = false)
	private Set<TomatoesGardenbed> tomatoesGardenbeds;

	public TomatoesVarietySpecification() {
	}

	public TomatoesVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public TomatoesVarietySpecification(String customId) {
		super(customId);
	}

	public TomatoesVarietySpecification(Integer id, String customId, Set<TomatoesGardenbed> tomatoesGardenbeds) {
		super(id, customId);
		this.tomatoesGardenbeds = tomatoesGardenbeds;
	}

	public TomatoesVarietySpecification(String customId, Set<TomatoesGardenbed> tomatoesGardenbeds) {
		super(customId);
		this.tomatoesGardenbeds = tomatoesGardenbeds;
	}

	public TomatoesVariety getTomatoesVariety() {
		return tomatoesVariety;
	}

	public void setTomatoesVariety(TomatoesVariety tomatoesVariety) {
		this.tomatoesVariety = tomatoesVariety;
	}

	public Set<TomatoesGardenbed> getTomatoesGardenbeds() {
		return tomatoesGardenbeds;
	}

	public void setTomatoesGardenbeds(Set<TomatoesGardenbed> tomatoesGardenbeds) {
		this.tomatoesGardenbeds = tomatoesGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tomatoesGardenbeds == null) ? 0 : tomatoesGardenbeds.hashCode());
		result = prime * result + ((tomatoesVariety == null) ? 0 : tomatoesVariety.hashCode());
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
		TomatoesVarietySpecification other = (TomatoesVarietySpecification) obj;
		if (tomatoesGardenbeds == null) {
			if (other.tomatoesGardenbeds != null)
				return false;
		} else if (!tomatoesGardenbeds.equals(other.tomatoesGardenbeds))
			return false;
		if (tomatoesVariety == null) {
			if (other.tomatoesVariety != null)
				return false;
		} else if (!tomatoesVariety.equals(other.tomatoesVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", TomatoesGardenbeds=" + tomatoesGardenbeds;
	}
}
