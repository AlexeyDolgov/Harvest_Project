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

import harvest.dao.PumpkinsVarietyRepository;
import harvest.domain.PumpkinsVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class PumpkinsVarietyService {
	Logger logger = LoggerFactory.getLogger(PumpkinsVarietyService.class);
	
	@Autowired
	private PumpkinsVarietyRepository pumpkinsVarietyRepository;

	public List<PumpkinsVariety> findAll() {
		logger.trace("Getting all pumpkins varieties from database...");
		
		return pumpkinsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(PumpkinsVariety pumpkinsVariety) {
    	logger.trace("Checking if stored pumpkins variety already exists in database...");
		
		Optional<PumpkinsVariety> pumpkinsVarietyFromDb = pumpkinsVarietyRepository.findByName(pumpkinsVariety.getName());
	
		if (pumpkinsVarietyFromDb.isPresent() && pumpkinsVariety.getId() != pumpkinsVarietyFromDb.get().getId()) {
			logger.warn("Pumpkins variety with title \"" + pumpkinsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createPumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Adding new pumpkins variety to database...");
		
		if (checkIfExists(pumpkinsVariety))
			return false;

		logger.trace("Saving new pumpkins variety in database...");
		pumpkinsVarietyRepository.save(pumpkinsVariety);
		return true;
	}

	public boolean updatePumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Updating pumpkins variety in database...");
		
		if (checkIfExists(pumpkinsVariety))
			return false;
		
		logger.trace("Saving updated pumpkins variety in database...");
		pumpkinsVarietyRepository.save(pumpkinsVariety);
		return true;
	}

	public void deletePumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Deleting pumpkins variety from database...");
		
		pumpkinsVarietyRepository.delete(pumpkinsVariety);
	}

	public List<PumpkinsVariety> mapPumpkinsVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping pumpkins varieties to domain objects...");
		
		List<PumpkinsVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			PumpkinsVariety pumpkinsVariety = new PumpkinsVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					pumpkinsVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					pumpkinsVariety.setName(entryValue);
				}
			}

			mappedList.add(pumpkinsVariety);
		}

		return mappedList;
	}
}
