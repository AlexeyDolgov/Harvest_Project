package harvest.domain;

import java.time.LocalDate;

public abstract class Harvesting {

	private Integer id;
	private LocalDate date;
	private Integer weight;

	public Harvesting() {
	}

	public Harvesting(Integer id, LocalDate date, Integer weight) {
		this.id = id;
		this.date = date;
		this.weight = weight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
