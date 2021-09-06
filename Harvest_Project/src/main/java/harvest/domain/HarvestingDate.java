package harvest.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "harvesting_date")
public class HarvestingDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "date_id")
	private Integer id;

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private LocalDate date;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "date")
	@Column(nullable = false)
	private Set<Harvesting> harvesting;


	public HarvestingDate() {
	}

	public HarvestingDate(Integer id, LocalDate date) {
		this.id = id;
		this.date = date;
	}

	public HarvestingDate(LocalDate date) {
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

	public Set<Harvesting> getHarvesting() {
		return harvesting;
	}

	public void setHarvesting(Set<Harvesting> harvesting) {
		this.harvesting = harvesting;
	}
}
