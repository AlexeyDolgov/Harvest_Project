package harvest.dto;

import java.util.List;

import harvest.domain.CucumbersVariety;

public class CucumbersVarietyImportForm {

	private List<CucumbersVariety> cucumbersVarieties;

	public List<CucumbersVariety> getCucumbersVarieties() {
		return cucumbersVarieties;
	}

	public void setCucumbersVarieties(List<CucumbersVariety> cucumbersVarieties) {
		this.cucumbersVarieties = cucumbersVarieties;
	}
}
