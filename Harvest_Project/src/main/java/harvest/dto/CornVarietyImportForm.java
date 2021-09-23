package harvest.dto;

import java.util.List;

import harvest.domain.CornVariety;

public class CornVarietyImportForm {

	private List<CornVariety> cornVarieties;

	public List<CornVariety> getCornVarieties() {
		return cornVarieties;
	}

	public void setCornVarieties(List<CornVariety> cornVarieties) {
		this.cornVarieties = cornVarieties;
	}
}
