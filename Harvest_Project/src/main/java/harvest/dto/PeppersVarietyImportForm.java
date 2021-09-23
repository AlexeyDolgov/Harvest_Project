package harvest.dto;

import java.util.List;

import harvest.domain.PeppersVariety;

public class PeppersVarietyImportForm {

	private List<PeppersVariety> peppersVarieties;

	public List<PeppersVariety> getPeppersVarieties() {
		return peppersVarieties;
	}

	public void setPeppersVarieties(List<PeppersVariety> peppersVarieties) {
		this.peppersVarieties = peppersVarieties;
	}
}
