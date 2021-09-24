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
@Table(name = "corn_variety_specification")
public class CornVarietySpecification extends VarietySpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_id")
	private CornVariety cornVariety;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cornVarietySpecification")
	@Column(nullable = false)
	private Set<CornGardenbed> cornGardenbeds;

	public CornVarietySpecification() {
	}

	public CornVarietySpecification(Integer id, String customId) {
		super(id, customId);
	}

	public CornVarietySpecification(String customId) {
		super(customId);
	}

	public CornVarietySpecification(Integer id, String customId, Set<CornGardenbed> cornGardenbeds) {
		super(id, customId);
		this.cornGardenbeds = cornGardenbeds;
	}

	public CornVarietySpecification(String customId, Set<CornGardenbed> cornGardenbeds) {
		super(customId);
		this.cornGardenbeds = cornGardenbeds;
	}

	public CornVariety getCornVariety() {
		return cornVariety;
	}

	public void setCornVariety(CornVariety cornVariety) {
		this.cornVariety = cornVariety;
	}

	public Set<CornGardenbed> getCornGardenbeds() {
		return cornGardenbeds;
	}

	public void setCornGardenbeds(Set<CornGardenbed> cornGardenbeds) {
		this.cornGardenbeds = cornGardenbeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cornGardenbeds == null) ? 0 : cornGardenbeds.hashCode());
		result = prime * result + ((cornVariety == null) ? 0 : cornVariety.hashCode());
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
		CornVarietySpecification other = (CornVarietySpecification) obj;
		if (cornGardenbeds == null) {
			if (other.cornGardenbeds != null)
				return false;
		} else if (!cornGardenbeds.equals(other.cornGardenbeds))
			return false;
		if (cornVariety == null) {
			if (other.cornVariety != null)
				return false;
		} else if (!cornVariety.equals(other.cornVariety))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", CustomId=" + getCustomId() + ", CornGardenbeds=" + cornGardenbeds;
	}
}
