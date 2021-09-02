package harvest.domain;

import java.util.Set;

public abstract class Variety {

	private Integer id;
	private String name;
	private Set<Place> place;

	public Variety() {
	}

	public Variety(Integer id, String name, Set<Place> place) {
		this.id = id;
		this.name = name;
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Place> getPlace() {
		return place;
	}

	public void setPlace(Set<Place> place) {
		this.place = place;
	}

}
