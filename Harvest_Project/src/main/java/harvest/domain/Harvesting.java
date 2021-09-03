package harvest.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Harvesting {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Integer id;

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private LocalDate date;

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private Integer weight;

	public Harvesting() {
	}

	public Harvesting(Integer id, LocalDate date, Integer weight) {
		this.id = id;
		this.date = date;
		this.weight = weight;
	}

	public Harvesting(LocalDate date, Integer weight) {
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
