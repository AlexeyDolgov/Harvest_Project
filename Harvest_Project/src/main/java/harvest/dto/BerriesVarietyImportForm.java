package harvest.dto;

import java.util.List;

import harvest.domain.BerriesVariety;

public class BerriesVarietyImportForm {

	private List<BerriesVariety> berriesVarieties;

	public List<BerriesVariety> getBerriesVarieties() {
		return berriesVarieties;
	}

	public void setBerriesVarieties(List<BerriesVariety> berriesVarieties) {
		this.berriesVarieties = berriesVarieties;
	}
}
