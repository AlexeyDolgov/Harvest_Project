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
@Table(name = "cabbage_variety")
public class CabbageVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<CabbageHarvesting> cabbage;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private CabbageSeason cabbageSeason;

	public CabbageVariety() {
		super();
	}

	public CabbageVariety(Integer id, String name) {
		super(id, name);
	}

	public CabbageVariety(String name) {
		super(name);
	}

	public Set<CabbageHarvesting> getCabbage() {
		return cabbage;
	}

	public void setCabbage(Set<CabbageHarvesting> cabbage) {
		this.cabbage = cabbage;
	}

	public CabbageSeason getCabbageSeason() {
		return cabbageSeason;
	}

	public void setCabbageSeason(CabbageSeason cabbageSeason) {
		this.cabbageSeason = cabbageSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cabbage == null) ? 0 : cabbage.hashCode());
		result = prime * result + ((cabbageSeason == null) ? 0 : cabbageSeason.hashCode());
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
		CabbageVariety other = (CabbageVariety) obj;
		if (cabbage == null) {
			if (other.cabbage != null)
				return false;
		} else if (!cabbage.equals(other.cabbage))
			return false;
		if (cabbageSeason == null) {
			if (other.cabbageSeason != null)
				return false;
		} else if (!cabbageSeason.equals(other.cabbageSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
