package harvest.dto;

import java.util.List;

import harvest.domain.GarlicVariety;

public class GarlicVarietyImportForm {

	private List<GarlicVariety> garlicVarieties;

	public List<GarlicVariety> getGarlicVarieties() {
		return garlicVarieties;
	}

	public void setGarlicVarieties(List<GarlicVariety> garlicVarieties) {
		this.garlicVarieties = garlicVarieties;
	}
}
