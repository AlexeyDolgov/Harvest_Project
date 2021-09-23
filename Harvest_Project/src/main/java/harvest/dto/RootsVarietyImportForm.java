package harvest.dto;

import java.util.List;

import harvest.domain.RootsVariety;

public class RootsVarietyImportForm {

	private List<RootsVariety> rootsVarieties;

	public List<RootsVariety> getRootsVarieties() {
		return rootsVarieties;
	}

	public void setRootsVarieties(List<RootsVariety> rootsVarieties) {
		this.rootsVarieties = rootsVarieties;
	}
}
