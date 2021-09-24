package harvest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VarietySpecification {

	@Id
	@SequenceGenerator(name = "varietySpecificationSequence", sequenceName = "sequence_variety_specification", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "varietySpecificationSequence")
	@Column(name = "variety_specification_id")
	private Integer id;

	@Column(nullable = false)
	@NotBlank(message = "Значение поля не может быть пустым!")
	private String customId;

	public VarietySpecification() {
		super();
	}

	public VarietySpecification(Integer id, String customId) {
		this.id = id;
		this.customId = customId;
	}

	public VarietySpecification(String customId) {
		this.customId = customId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customId == null) ? 0 : customId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VarietySpecification other = (VarietySpecification) obj;
		if (customId == null) {
			if (other.customId != null)
				return false;
		} else if (!customId.equals(other.customId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
