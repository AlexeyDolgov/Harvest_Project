package harvest.dto;

import java.util.List;

import harvest.domain.CarrotsVariety;

public class CarrotsVarietyImportForm {

	private List<CarrotsVariety> carrotsVarieties;

	public List<CarrotsVariety> getCarrotsVarieties() {
		return carrotsVarieties;
	}

	public void setCarrotsVarieties(List<CarrotsVariety> carrotsVarieties) {
		this.carrotsVarieties = carrotsVarieties;
	}
}
