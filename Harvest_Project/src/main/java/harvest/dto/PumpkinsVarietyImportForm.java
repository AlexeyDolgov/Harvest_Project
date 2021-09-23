package harvest.dto;

import java.util.List;

import harvest.domain.PumpkinsVariety;

public class PumpkinsVarietyImportForm {

	private List<PumpkinsVariety> pumpkinsVarieties;

	public List<PumpkinsVariety> getPumpkinsVarieties() {
		return pumpkinsVarieties;
	}

	public void setPumpkinsVarieties(List<PumpkinsVariety> pumpkinsVarieties) {
		this.pumpkinsVarieties = pumpkinsVarieties;
	}
}
