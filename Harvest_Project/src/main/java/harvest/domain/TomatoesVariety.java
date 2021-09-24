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
@Table(name = "tomatoes_variety")
public class TomatoesVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<TomatoesHarvesting> tomatoes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private TomatoesSeason tomatoesSeason;

	public TomatoesVariety() {
		super();
	}

	public TomatoesVariety(Integer id, String name) {
		super(id, name);
	}

	public TomatoesVariety(String name) {
		super(name);
	}

	public Set<TomatoesHarvesting> getTomatoes() {
		return tomatoes;
	}

	public void setTomatoes(Set<TomatoesHarvesting> tomatoes) {
		this.tomatoes = tomatoes;
	}

	public TomatoesSeason getTomatoesSeason() {
		return tomatoesSeason;
	}

	public void setTomatoesSeason(TomatoesSeason tomatoesSeason) {
		this.tomatoesSeason = tomatoesSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tomatoes == null) ? 0 : tomatoes.hashCode());
		result = prime * result + ((tomatoesSeason == null) ? 0 : tomatoesSeason.hashCode());
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
		TomatoesVariety other = (TomatoesVariety) obj;
		if (tomatoes == null) {
			if (other.tomatoes != null)
				return false;
		} else if (!tomatoes.equals(other.tomatoes))
			return false;
		if (tomatoesSeason == null) {
			if (other.tomatoesSeason != null)
				return false;
		} else if (!tomatoesSeason.equals(other.tomatoesSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
