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
@Table(name = "potatoes_variety")
public class PotatoesVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<PotatoesHarvesting> potatoes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private PotatoesSeason potatoesSeason;

	public PotatoesVariety() {
		super();
	}

	public PotatoesVariety(Integer id, String name) {
		super(id, name);
	}

	public PotatoesVariety(String name) {
		super(name);
	}

	public Set<PotatoesHarvesting> getPotatoes() {
		return potatoes;
	}

	public void setPotatoes(Set<PotatoesHarvesting> potatoes) {
		this.potatoes = potatoes;
	}

	public PotatoesSeason getPotatoesSeason() {
		return potatoesSeason;
	}

	public void setPotatoesSeason(PotatoesSeason potatoesSeason) {
		this.potatoesSeason = potatoesSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((potatoes == null) ? 0 : potatoes.hashCode());
		result = prime * result + ((potatoesSeason == null) ? 0 : potatoesSeason.hashCode());
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
		PotatoesVariety other = (PotatoesVariety) obj;
		if (potatoes == null) {
			if (other.potatoes != null)
				return false;
		} else if (!potatoes.equals(other.potatoes))
			return false;
		if (potatoesSeason == null) {
			if (other.potatoesSeason != null)
				return false;
		} else if (!potatoesSeason.equals(other.potatoesSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
