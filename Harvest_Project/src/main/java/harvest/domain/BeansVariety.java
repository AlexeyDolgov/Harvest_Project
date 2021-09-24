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
@Table(name = "beans_variety")
public class BeansVariety extends Variety implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "variety")
	@Column(nullable = false)
	private Set<BeansHarvesting> beans;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	private BeansSeason beansSeason;

	public BeansVariety() {
		super();
	}

	public BeansVariety(Integer id, String name) {
		super(id, name);
	}

	public BeansVariety(String name) {
		super(name);
	}

	public Set<BeansHarvesting> getBeans() {
		return beans;
	}

	public void setBeans(Set<BeansHarvesting> beans) {
		this.beans = beans;
	}

	public BeansSeason getBeansSeason() {
		return beansSeason;
	}

	public void setBeansSeason(BeansSeason beansSeason) {
		this.beansSeason = beansSeason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beans == null) ? 0 : beans.hashCode());
		result = prime * result + ((beansSeason == null) ? 0 : beansSeason.hashCode());
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
		BeansVariety other = (BeansVariety) obj;
		if (beans == null) {
			if (other.beans != null)
				return false;
		} else if (!beans.equals(other.beans))
			return false;
		if (beansSeason == null) {
			if (other.beansSeason != null)
				return false;
		} else if (!beansSeason.equals(other.beansSeason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName();
	}
}
