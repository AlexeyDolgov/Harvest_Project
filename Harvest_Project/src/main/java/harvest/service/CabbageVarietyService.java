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

import harvest.dao.CabbageVarietyRepository;
import harvest.domain.CabbageVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class CabbageVarietyService {
	Logger logger = LoggerFactory.getLogger(CabbageVarietyService.class);
	
	@Autowired
	private CabbageVarietyRepository cabbageVarietyRepository;

	public List<CabbageVariety> findAll() {
		logger.trace("Getting all cabbage varieties from database...");
		
		return cabbageVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(CabbageVariety cabbageVariety) {
    	logger.trace("Checking if stored cabbage variety already exists in database...");
		
		Optional<CabbageVariety> cabbageVarietyFromDb = cabbageVarietyRepository.findByName(cabbageVariety.getName());
	
		if (cabbageVarietyFromDb.isPresent() && cabbageVariety.getId() != cabbageVarietyFromDb.get().getId()) {
			logger.warn("Cabbage variety with title \"" + cabbageVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createCabbageVariety(CabbageVariety cabbageVariety) {
		logger.trace("Adding new cabbage variety to database...");
		
		if (checkIfExists(cabbageVariety))
			return false;

		logger.trace("Saving new cabbage variety in database...");
		cabbageVarietyRepository.save(cabbageVariety);
		return true;
	}

	public boolean updateCabbageVariety(CabbageVariety cabbageVariety) {
		logger.trace("Updating cabbage variety in database...");
		
		if (checkIfExists(cabbageVariety))
			return false;
		
		logger.trace("Saving updated cabbage variety in database...");
		cabbageVarietyRepository.save(cabbageVariety);
		return true;
	}

	public void deleteCabbageVariety(CabbageVariety cabbageVariety) {
		logger.trace("Deleting cabbage variety from database...");
		
		cabbageVarietyRepository.delete(cabbageVariety);
	}

	public List<CabbageVariety> mapCabbageVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping cabbage varieties to domain objects...");
		
		List<CabbageVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			CabbageVariety cabbageVariety = new CabbageVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					cabbageVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					cabbageVariety.setName(entryValue);
				}
			}

			mappedList.add(cabbageVariety);
		}

		return mappedList;
	}
}
