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
@Table(name = "pumpkins_variety")
public class PumpkinsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<PumpkinsHarvesting> pumpkins;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private PumpkinsSeason pumpkinsSeason;

	public PumpkinsVariety() {
		super();
	}

	public PumpkinsVariety(Integer id, String name) {
		super(id, name);
	}

	public PumpkinsVariety(String name) {
		super(name);
	}

	public Set<PumpkinsHarvesting> getPumpkins() {
		return pumpkins;
	}

	public void setPumpkins(Set<PumpkinsHarvesting> pumpkins) {
		this.pumpkins = pumpkins;
	}

	public PumpkinsSeason getPumpkinsSeason() {
		return pumpkinsSeason;
	}

	public void setPumpkinsSeason(PumpkinsSeason pumpkinsSeason) {
		this.pumpkinsSeason = pumpkinsSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pumpkins == null) ? 0 : pumpkins.hashCode());
		result = prime * result + ((pumpkinsSeason == null) ? 0 : pumpkinsSeason.hashCode());
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
		PumpkinsVariety other = (PumpkinsVariety) obj;
		if (pumpkins == null) {
			if (other.pumpkins != null)
				return false;
		} else if (!pumpkins.equals(other.pumpkins))
			return false;
		if (pumpkinsSeason == null) {
			if (other.pumpkinsSeason != null)
				return false;
		} else if (!pumpkinsSeason.equals(other.pumpkinsSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
