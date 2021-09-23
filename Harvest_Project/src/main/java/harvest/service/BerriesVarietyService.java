package harvest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.BerriesVarietyRepository;
import harvest.domain.BerriesVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class BerriesVarietyService {
	Logger logger = LoggerFactory.getLogger(BerriesVarietyService.class);
	
	@Autowired
	private BerriesVarietyRepository berriesVarietyRepository;

	public List<BerriesVariety> findAll() {
		logger.trace("Getting all berries varieties from database...");
		
		return berriesVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(BerriesVariety berriesVariety) {
    	logger.trace("Checking if stored berries variety already exists in database...");
		
		Optional<BerriesVariety> berriesVarietyFromDb = berriesVarietyRepository.findByName(berriesVariety.getName());
	
		if (berriesVarietyFromDb.isPresent() && berriesVariety.getId() != berriesVarietyFromDb.get().getId()) {
			logger.warn("Berries variety with title \"" + berriesVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createBerriesVariety(BerriesVariety berriesVariety) {
		logger.trace("Adding new berries variety to database...");
		
		if (checkIfExists(berriesVariety))
			return false;

		logger.trace("Saving new berries variety in database...");
		berriesVarietyRepository.save(berriesVariety);
		return true;
	}

	public boolean updateBerriesVariety(BerriesVariety berriesVariety) {
		logger.trace("Updating berries variety in database...");
		
		if (checkIfExists(berriesVariety))
			return false;
		
		logger.trace("Saving updated berries variety in database...");
		berriesVarietyRepository.save(berriesVariety);
		return true;
	}

	public void deleteBerriesVariety(BerriesVariety berriesVariety) {
		logger.trace("Deleting berries variety from database...");
		
		berriesVarietyRepository.delete(berriesVariety);
	}

	public List<BerriesVariety> mapBerriesVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping berries varieties to domain objects...");
		
		List<BerriesVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			BerriesVariety berriesVariety = new BerriesVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					berriesVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					berriesVariety.setName(entryValue);
				}
			}

			mappedList.add(berriesVariety);
		}

		return mappedList;
	}
}
