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
@Table(name = "beans_variety_specification")
public class BeansVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private BeansVariety beansVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "beansVarietySpecification")
	@Column(nullable = false)
	private Set<BeansGardenbed> beansGardenbeds;

	public BeansVarietySpecification() {
	}

	public BeansVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public BeansVarietySpecification(String customId) {
		super(customId);
	}

	public BeansVarietySpecification(Integer id, String customId, Set<BeansGardenbed> beansGardenbeds) {
		super(id, customId);
		this.beansGardenbeds = beansGardenbeds;
	}

	public BeansVarietySpecification(String customId, Set<BeansGardenbed> beansGardenbeds) {
		super(customId);
		this.beansGardenbeds = beansGardenbeds;
	}

	public BeansVariety getBeansVariety() {
		return beansVariety;
	}

	public void setBeansVariety(BeansVariety beansVariety) {
		this.beansVariety = beansVariety;
	}

	public Set<BeansGardenbed> getBeansGardenbeds() {
		return beansGardenbeds;
	}

	public void setBeansGardenbeds(Set<BeansGardenbed> beansGardenbeds) {
		this.beansGardenbeds = beansGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beansGardenbeds == null) ? 0 : beansGardenbeds.hashCode());
		result = prime * result + ((beansVariety == null) ? 0 : beansVariety.hashCode());
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
		BeansVarietySpecification other = (BeansVarietySpecification) obj;
		if (beansGardenbeds == null) {
			if (other.beansGardenbeds != null)
				return false;
		} else if (!beansGardenbeds.equals(other.beansGardenbeds))
			return false;
		if (beansVariety == null) {
			if (other.beansVariety != null)
				return false;
		} else if (!beansVariety.equals(other.beansVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", BeansGardenbeds=" + beansGardenbeds;
	}
}
