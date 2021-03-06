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
@Table(name = "garlic_variety")
public class GarlicVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<GarlicHarvesting> garlic;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private GarlicSeason garlicSeason;

	public GarlicVariety() {
		super();
	}

	public GarlicVariety(Integer id, String name) {
		super(id, name);
	}

	public GarlicVariety(String name) {
		super(name);
	}

	public Set<GarlicHarvesting> getGarlic() {
		return garlic;
	}

	public void setGarlic(Set<GarlicHarvesting> garlic) {
		this.garlic = garlic;
	}

	public GarlicSeason getGarlicSeason() {
		return garlicSeason;
	}

	public void setGarlicSeason(GarlicSeason garlicSeason) {
		this.garlicSeason = garlicSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((garlic == null) ? 0 : garlic.hashCode());
		result = prime * result + ((garlicSeason == null) ? 0 : garlicSeason.hashCode());
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
		GarlicVariety other = (GarlicVariety) obj;
		if (garlic == null) {
			if (other.garlic != null)
				return false;
		} else if (!garlic.equals(other.garlic))
			return false;
		if (garlicSeason == null) {
			if (other.garlicSeason != null)
				return false;
		} else if (!garlicSeason.equals(other.garlicSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
