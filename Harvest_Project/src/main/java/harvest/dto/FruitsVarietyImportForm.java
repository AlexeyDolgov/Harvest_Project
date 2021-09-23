package harvest.dto;

import java.util.List;

import harvest.domain.FruitsVariety;

public class FruitsVarietyImportForm {

	private List<FruitsVariety> fruitsVarieties;

	public List<FruitsVariety> getFruitsVarieties() {
		return fruitsVarieties;
	}

	public void setFruitsVarieties(List<FruitsVariety> fruitsVarieties) {
		this.fruitsVarieties = fruitsVarieties;
	}
}
