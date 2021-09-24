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
@Table(name = "fruits_variety_specification")
public class FruitsVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private FruitsVariety fruitsVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fruitsVarietySpecification")
	@Column(nullable = false)
	private Set<FruitsGardenbed> fruitsGardenbeds;

	public FruitsVarietySpecification() {
	}

	public FruitsVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public FruitsVarietySpecification(String customId) {
		super(customId);
	}

	public FruitsVarietySpecification(Integer id, String customId, Set<FruitsGardenbed> fruitsGardenbeds) {
		super(id, customId);
		this.fruitsGardenbeds = fruitsGardenbeds;
	}

	public FruitsVarietySpecification(String customId, Set<FruitsGardenbed> fruitsGardenbeds) {
		super(customId);
		this.fruitsGardenbeds = fruitsGardenbeds;
	}

	public FruitsVariety getFruitsVariety() {
		return fruitsVariety;
	}

	public void setFruitsVariety(FruitsVariety fruitsVariety) {
		this.fruitsVariety = fruitsVariety;
	}

	public Set<FruitsGardenbed> getFruitsGardenbeds() {
		return fruitsGardenbeds;
	}

	public void setFruitsGardenbeds(Set<FruitsGardenbed> fruitsGardenbeds) {
		this.fruitsGardenbeds = fruitsGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fruitsGardenbeds == null) ? 0 : fruitsGardenbeds.hashCode());
		result = prime * result + ((fruitsVariety == null) ? 0 : fruitsVariety.hashCode());
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
		FruitsVarietySpecification other = (FruitsVarietySpecification) obj;
		if (fruitsGardenbeds == null) {
			if (other.fruitsGardenbeds != null)
				return false;
		} else if (!fruitsGardenbeds.equals(other.fruitsGardenbeds))
			return false;
		if (fruitsVariety == null) {
			if (other.fruitsVariety != null)
				return false;
		} else if (!fruitsVariety.equals(other.fruitsVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", FruitsGardenbeds=" + fruitsGardenbeds;
	}
}
