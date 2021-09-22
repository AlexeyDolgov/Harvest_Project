package harvest.dto;

import java.util.List;

import harvest.domain.TomatoesVariety;

public class TomatoesVarietyImportForm {

	private List<TomatoesVariety> tomatoesVarieties;

	public List<TomatoesVariety> getTomatoesVarieties() {
		return tomatoesVarieties;
	}

	public void setTomatoesVarieties(List<TomatoesVariety> tomatoesVarieties) {
		this.tomatoesVarieties = tomatoesVarieties;
	}
}
