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
@Table(name = "berries_variety")
public class BerriesVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BerriesHarvesting> berries;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private BerriesSeason berriesSeason;

	public BerriesVariety() {
		super();
	}

	public BerriesVariety(Integer id, String name) {
		super(id, name);
	}

	public BerriesVariety(String name) {
		super(name);
	}

	public Set<BerriesHarvesting> getBerries() {
		return berries;
	}

	public void setBerries(Set<BerriesHarvesting> berries) {
		this.berries = berries;
	}

	public BerriesSeason getBerriesSeason() {
		return berriesSeason;
	}

	public void setBerriesSeason(BerriesSeason berriesSeason) {
		this.berriesSeason = berriesSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((berries == null) ? 0 : berries.hashCode());
		result = prime * result + ((berriesSeason == null) ? 0 : berriesSeason.hashCode());
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
		BerriesVariety other = (BerriesVariety) obj;
		if (berries == null) {
			if (other.berries != null)
				return false;
		} else if (!berries.equals(other.berries))
			return false;
		if (berriesSeason == null) {
			if (other.berriesSeason != null)
				return false;
		} else if (!berriesSeason.equals(other.berriesSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
