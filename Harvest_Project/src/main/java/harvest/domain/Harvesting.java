package harvest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Harvesting {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Integer id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "date_id")	
	private HarvestingDate date;

	@Column
	@NotBlank(message = "Значение поля не может быть пустым!")
	private Integer weight;

	public Harvesting() {
	}

	public Harvesting(Integer id, HarvestingDate date, Integer weight) {
		this.id = id;
		this.date = date;
		this.weight = weight;
	}

	public Harvesting(HarvestingDate date, Integer weight) {
		this.date = date;
		this.weight = weight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HarvestingDate getDate() {
		return date;
	}

	public void setDate(HarvestingDate date) {
		this.date = date;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
