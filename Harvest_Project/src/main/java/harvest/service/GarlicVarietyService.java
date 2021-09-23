package harvest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.GarlicVarietyRepository;
import harvest.domain.GarlicVariety;
import harvest.domain.TomatoesVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class GarlicVarietyService {
	Logger logger = LoggerFactory.getLogger(GarlicVarietyService.class);
	
	@Autowired
	private GarlicVarietyRepository garlicVarietyRepository;

	public List<GarlicVariety> findAll() {
		logger.trace("Getting all garlic varieties from database...");
		
		return garlicVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(GarlicVariety garlicVariety) {
    	logger.trace("Checking if stored garlic variety already exists in database...");
		
		Optional<GarlicVariety> garlicVarietyFromDb = garlicVarietyRepository.findByName(garlicVariety.getName());
	
		if (garlicVarietyFromDb.isPresent() && garlicVariety.getId() != garlicVarietyFromDb.get().getId()) {
			logger.warn("Garlic variety with title \"" + garlicVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createGarlicVariety(GarlicVariety garlicVariety) {
		logger.trace("Adding new garlic variety to database...");
		
		if (checkIfExists(garlicVariety))
			return false;

		logger.trace("Saving new garlic variety in database...");
		garlicVarietyRepository.save(garlicVariety);
		return true;
	}

	public boolean updateGarlicVariety(GarlicVariety garlicVariety) {
		logger.trace("Updating garlic variety in database...");
		
		if (checkIfExists(garlicVariety))
			return false;
		
		logger.trace("Saving updated garlic variety in database...");
		garlicVarietyRepository.save(garlicVariety);
		return true;
	}

	public void deleteGarlicVariety(GarlicVariety garlicVariety) {
		logger.trace("Deleting garlic variety from database...");
		
		garlicVarietyRepository.delete(garlicVariety);
	}

	public List<GarlicVariety> mapGarlicVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping garlic varieties to domain objects...");
		
		List<GarlicVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			GarlicVariety garlicVariety = new GarlicVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					garlicVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					garlicVariety.setName(entryValue);
				}
			}

			mappedList.add(garlicVariety);
		}

		return mappedList;
	}
}
