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
@Table(name = "squash_variety_specification")
public class SquashVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private SquashVariety squashVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "squashVarietySpecification")
	@Column(nullable = false)
	private Set<SquashGardenbed> squashGardenbeds;

	public SquashVarietySpecification() {
	}

	public SquashVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public SquashVarietySpecification(String customId) {
		super(customId);
	}

	public SquashVarietySpecification(Integer id, String customId, Set<SquashGardenbed> squashGardenbeds) {
		super(id, customId);
		this.squashGardenbeds = squashGardenbeds;
	}

	public SquashVarietySpecification(String customId, Set<SquashGardenbed> squashGardenbeds) {
		super(customId);
		this.squashGardenbeds = squashGardenbeds;
	}

	public SquashVariety getSquashVariety() {
		return squashVariety;
	}

	public void setSquashVariety(SquashVariety squashVariety) {
		this.squashVariety = squashVariety;
	}

	public Set<SquashGardenbed> getSquashGardenbeds() {
		return squashGardenbeds;
	}

	public void setSquashGardenbeds(Set<SquashGardenbed> squashGardenbeds) {
		this.squashGardenbeds = squashGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((squashGardenbeds == null) ? 0 : squashGardenbeds.hashCode());
		result = prime * result + ((squashVariety == null) ? 0 : squashVariety.hashCode());
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
		SquashVarietySpecification other = (SquashVarietySpecification) obj;
		if (squashGardenbeds == null) {
			if (other.squashGardenbeds != null)
				return false;
		} else if (!squashGardenbeds.equals(other.squashGardenbeds))
			return false;
		if (squashVariety == null) {
			if (other.squashVariety != null)
				return false;
		} else if (!squashVariety.equals(other.squashVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", SquashGardenbeds=" + squashGardenbeds;
	}
}
