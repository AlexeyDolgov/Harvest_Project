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
@Table(name = "squash_variety")
public class SquashVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<SquashHarvesting> squash;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private SquashSeason squashSeason;
	
	public SquashVariety() {
		super();
	}

	public SquashVariety(Integer id, String name) {
		super(id, name);
	}

	public SquashVariety(String name) {
		super(name);
	}

	public Set<SquashHarvesting> getSquash() {
		return squash;
	}

	public void setSquash(Set<SquashHarvesting> squash) {
		this.squash = squash;
	}

	public SquashSeason getSquashSeason() {
		return squashSeason;
	}

	public void setSquashSeason(SquashSeason squashSeason) {
		this.squashSeason = squashSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((squash == null) ? 0 : squash.hashCode());
		result = prime * result + ((squashSeason == null) ? 0 : squashSeason.hashCode());
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
		SquashVariety other = (SquashVariety) obj;
		if (squash == null) {
			if (other.squash != null)
				return false;
		} else if (!squash.equals(other.squash))
			return false;
		if (squashSeason == null) {
			if (other.squashSeason != null)
				return false;
		} else if (!squashSeason.equals(other.squashSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
