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
@Table(name = "corn_variety")
public class CornVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CornHarvesting> corn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private CornSeason cornSeason;
	
	public CornVariety() {
		super();
	}

	public CornVariety(Integer id, String name) {
		super(id, name);
	}

	public CornVariety(String name) {
		super(name);
	}

	public Set<CornHarvesting> getCorn() {
		return corn;
	}

	public void setCorn(Set<CornHarvesting> corn) {
		this.corn = corn;
	}

	public CornSeason getCornSeason() {
		return cornSeason;
	}

	public void setCornSeason(CornSeason cornSeason) {
		this.cornSeason = cornSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((corn == null) ? 0 : corn.hashCode());
		result = prime * result + ((cornSeason == null) ? 0 : cornSeason.hashCode());
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
		CornVariety other = (CornVariety) obj;
		if (corn == null) {
			if (other.corn != null)
				return false;
		} else if (!corn.equals(other.corn))
			return false;
		if (cornSeason == null) {
			if (other.cornSeason != null)
				return false;
		} else if (!cornSeason.equals(other.cornSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
