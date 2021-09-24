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
@Table(name = "beetroots_variety")
public class BeetrootsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BeetrootsHarvesting> beetroots;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private BeetrootsSeason beetrootsSeason;

	public BeetrootsVariety() {
		super();
	}

	public BeetrootsVariety(Integer id, String name) {
		super(id, name);
	}

	public BeetrootsVariety(String name) {
		super(name);
	}

	public Set<BeetrootsHarvesting> getBeetroots() {
		return beetroots;
	}

	public void setBeetroots(Set<BeetrootsHarvesting> beetroots) {
		this.beetroots = beetroots;
	}

	public BeetrootsSeason getBeetrootsSeason() {
		return beetrootsSeason;
	}

	public void setBeetrootsSeason(BeetrootsSeason beetrootsSeason) {
		this.beetrootsSeason = beetrootsSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beetroots == null) ? 0 : beetroots.hashCode());
		result = prime * result + ((beetrootsSeason == null) ? 0 : beetrootsSeason.hashCode());
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
		BeetrootsVariety other = (BeetrootsVariety) obj;
		if (beetroots == null) {
			if (other.beetroots != null)
				return false;
		} else if (!beetroots.equals(other.beetroots))
			return false;
		if (beetrootsSeason == null) {
			if (other.beetrootsSeason != null)
				return false;
		} else if (!beetrootsSeason.equals(other.beetrootsSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
