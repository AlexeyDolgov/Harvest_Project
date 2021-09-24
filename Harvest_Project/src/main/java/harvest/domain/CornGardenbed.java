package harvest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "corn_gardenbed")
public class CornGardenbed extends Gardenbed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Integer numberOfPlants;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "variety_specification_id")
	private CornVarietySpecification cornVarietySpecification;

	public CornGardenbed() {
		super();
	}

	public CornGardenbed(Integer id, String comment, Place place) {
		super(id, comment, place);
	}

	public CornGardenbed(Integer id, Place place) {
		super(id, place);
	}

	public CornGardenbed(String comment, Place place) {
		super(comment, place);
	}

	public CornGardenbed(Place place) {
		super(place);
	}

	public CornGardenbed(Integer id, String comment, Place place, Integer numberOfPlants) {
		super(id, comment, place);
		this.numberOfPlants = numberOfPlants;
	}

	public CornGardenbed(Integer id, Place place, Integer numberOfPlants) {
		super(id, place);
		this.numberOfPlants = numberOfPlants;
	}

	public CornGardenbed(String comment, Place place, Integer numberOfPlants) {
		super(comment, place);
		this.numberOfPlants = numberOfPlants;
	}

	public CornGardenbed(Place place, Integer numberOfPlants) {
		super(place);
		this.numberOfPlants = numberOfPlants;
	}

	public Integer getNumberOfPlants() {
		return numberOfPlants;
	}

	public void setNumberOfPlants(Integer numberOfPlants) {
		this.numberOfPlants = numberOfPlants;
	}

	public CornVarietySpecification getCornVarietySpecification() {
		return cornVarietySpecification;
	}

	public void setCornVarietySpecification(CornVarietySpecification cornVarietySpecification) {
		this.cornVarietySpecification = cornVarietySpecification;
	}


	@Override
	public String toString() {
		return "Id=" + getId() + ", Comment=" + getComment() + ", Place=" + getPlace() + ", NumberOfPlants=" + numberOfPlants;
	}
}
