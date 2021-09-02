package harvest.domain;

import java.time.LocalDate;

public class HarvestingDates {

	private Integer id;
	private LocalDate date;

	public HarvestingDates() {
	}

	public HarvestingDates(Integer id, LocalDate date) {
		this.id = id;
		this.date = date;
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

}
