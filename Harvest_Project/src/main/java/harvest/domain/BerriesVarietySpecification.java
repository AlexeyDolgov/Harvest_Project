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
@Table(name = "berries_variety_specification")
public class BerriesVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BerriesVariety berriesVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "berriesVarietySpecification")
	@Column(nullable = false)
	private Set<BerriesGardenbed> berriesGardenbeds;

	public BerriesVarietySpecification() {
	}

	public BerriesVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public BerriesVarietySpecification(String customId) {
		super(customId);
	}

	public BerriesVarietySpecification(Integer id, String customId, Set<BerriesGardenbed> berriesGardenbeds) {
		super(id, customId);
		this.berriesGardenbeds = berriesGardenbeds;
	}

	public BerriesVarietySpecification(String customId, Set<BerriesGardenbed> berriesGardenbeds) {
		super(customId);
		this.berriesGardenbeds = berriesGardenbeds;
	}

	public BerriesVariety getBerriesVariety() {
		return berriesVariety;
	}

	public void setBerriesVariety(BerriesVariety berriesVariety) {
		this.berriesVariety = berriesVariety;
	}

	public Set<BerriesGardenbed> getBerriesGardenbeds() {
		return berriesGardenbeds;
	}

	public void setBerriesGardenbeds(Set<BerriesGardenbed> berriesGardenbeds) {
		this.berriesGardenbeds = berriesGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((berriesGardenbeds == null) ? 0 : berriesGardenbeds.hashCode());
		result = prime * result + ((berriesVariety == null) ? 0 : berriesVariety.hashCode());
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
		BerriesVarietySpecification other = (BerriesVarietySpecification) obj;
		if (berriesGardenbeds == null) {
			if (other.berriesGardenbeds != null)
				return false;
		} else if (!berriesGardenbeds.equals(other.berriesGardenbeds))
			return false;
		if (berriesVariety == null) {
			if (other.berriesVariety != null)
				return false;
		} else if (!berriesVariety.equals(other.berriesVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", BerriesGardenbeds=" + berriesGardenbeds;
	}
}
