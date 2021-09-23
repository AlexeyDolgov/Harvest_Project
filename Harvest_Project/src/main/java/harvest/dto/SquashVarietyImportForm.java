package harvest.dto;

import java.util.List;

import harvest.domain.SquashVariety;

public class SquashVarietyImportForm {

	private List<SquashVariety> squashVarieties;

	public List<SquashVariety> getSquashVarieties() {
		return squashVarieties;
	}

	public void setSquashVarieties(List<SquashVariety> squashVarieties) {
		this.squashVarieties = squashVarieties;
	}
}
