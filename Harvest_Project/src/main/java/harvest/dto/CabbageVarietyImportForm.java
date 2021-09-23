package harvest.dto;

import java.util.List;

import harvest.domain.CabbageVariety;

public class CabbageVarietyImportForm {

	private List<CabbageVariety> cabbageVarieties;

	public List<CabbageVariety> getCabbageVarieties() {
		return cabbageVarieties;
	}

	public void setCabbageVarieties(List<CabbageVariety> cabbageVarieties) {
		this.cabbageVarieties = cabbageVarieties;
	}
}
