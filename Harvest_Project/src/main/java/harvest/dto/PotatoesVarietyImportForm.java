package harvest.dto;

import java.util.List;

import harvest.domain.PotatoesVariety;

public class PotatoesVarietyImportForm {

	private List<PotatoesVariety> potatoesVarieties;

	public List<PotatoesVariety> getPotatoesVarieties() {
		return potatoesVarieties;
	}

	public void setPotatoesVarieties(List<PotatoesVariety> potatoesVarieties) {
		this.potatoesVarieties = potatoesVarieties;
	}
}
