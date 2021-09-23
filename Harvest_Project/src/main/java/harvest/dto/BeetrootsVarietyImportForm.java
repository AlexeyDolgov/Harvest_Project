package harvest.dto;

import java.util.List;

import harvest.domain.BeetrootsVariety;

public class BeetrootsVarietyImportForm {

	private List<BeetrootsVariety> beetrootsVarieties;

	public List<BeetrootsVariety> getBeetrootsVarieties() {
		return beetrootsVarieties;
	}

	public void setBeetrootsVarieties(List<BeetrootsVariety> beetrootsVarieties) {
		this.beetrootsVarieties = beetrootsVarieties;
	}
}
