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

import harvest.dao.BeetrootsVarietyRepository;
import harvest.domain.BeetrootsVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class BeetrootsVarietyService {
	Logger logger = LoggerFactory.getLogger(BeetrootsVarietyService.class);
	
	@Autowired
	private BeetrootsVarietyRepository beetrootsVarietyRepository;

	public List<BeetrootsVariety> findAll() {
		logger.trace("Getting all beetroots varieties from database...");
		
		return beetrootsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(BeetrootsVariety beetrootsVariety) {
    	logger.trace("Checking if stored beetroots variety already exists in database...");
		
		Optional<BeetrootsVariety> beetrootsVarietyFromDb = beetrootsVarietyRepository.findByName(beetrootsVariety.getName());
	
		if (beetrootsVarietyFromDb.isPresent() && beetrootsVariety.getId() != beetrootsVarietyFromDb.get().getId()) {
			logger.warn("Beetroots variety with title \"" + beetrootsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createBeetrootsVariety(BeetrootsVariety beetrootsVariety) {
		logger.trace("Adding new beetroots variety to database...");
		
		if (checkIfExists(beetrootsVariety))
			return false;

		logger.trace("Saving new beetroots variety in database...");
		beetrootsVarietyRepository.save(beetrootsVariety);
		return true;
	}

	public boolean updateBeetrootsVariety(BeetrootsVariety beetrootsVariety) {
		logger.trace("Updating beetroots variety in database...");
		
		if (checkIfExists(beetrootsVariety))
			return false;
		
		logger.trace("Saving updated beetroots variety in database...");
		beetrootsVarietyRepository.save(beetrootsVariety);
		return true;
	}

	public void deleteBeetrootsVariety(BeetrootsVariety beetrootsVariety) {
		logger.trace("Deleting beetroots variety from database...");
		
		beetrootsVarietyRepository.delete(beetrootsVariety);
	}

	public List<BeetrootsVariety> mapBeetrootsVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping beetroots varieties to domain objects...");
		
		List<BeetrootsVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			BeetrootsVariety beetrootsVariety = new BeetrootsVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					beetrootsVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					beetrootsVariety.setName(entryValue);
				}
			}

			mappedList.add(beetrootsVariety);
		}

		return mappedList;
	}
}
