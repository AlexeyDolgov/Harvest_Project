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
@Table(name = "roots_variety")
public class RootsVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<RootsHarvesting> roots;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private RootsSeason rootsSeason;

	public RootsVariety() {
		super();
	}

	public RootsVariety(Integer id, String name) {
		super(id, name);
	}

	public RootsVariety(String name) {
		super(name);
	}

	public Set<RootsHarvesting> getRoots() {
		return roots;
	}

	public void setRoots(Set<RootsHarvesting> roots) {
		this.roots = roots;
	}

	public RootsSeason getRootsSeason() {
		return rootsSeason;
	}

	public void setRootsSeason(RootsSeason rootsSeason) {
		this.rootsSeason = rootsSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roots == null) ? 0 : roots.hashCode());
		result = prime * result + ((rootsSeason == null) ? 0 : rootsSeason.hashCode());
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
		RootsVariety other = (RootsVariety) obj;
		if (roots == null) {
			if (other.roots != null)
				return false;
		} else if (!roots.equals(other.roots))
			return false;
		if (rootsSeason == null) {
			if (other.rootsSeason != null)
				return false;
		} else if (!rootsSeason.equals(other.rootsSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
