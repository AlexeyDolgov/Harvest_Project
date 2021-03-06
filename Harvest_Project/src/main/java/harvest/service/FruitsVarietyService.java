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

import harvest.dao.FruitsVarietyRepository;
import harvest.domain.FruitsVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class FruitsVarietyService {
	Logger logger = LoggerFactory.getLogger(FruitsVarietyService.class);
	
	@Autowired
	private FruitsVarietyRepository fruitsVarietyRepository;

	public List<FruitsVariety> findAll() {
		logger.trace("Getting all fruits varieties from database...");
		
		return fruitsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(FruitsVariety fruitsVariety) {
    	logger.trace("Checking if stored fruits variety already exists in database...");
		
		Optional<FruitsVariety> fruitsVarietyFromDb = fruitsVarietyRepository.findByName(fruitsVariety.getName());
	
		if (fruitsVarietyFromDb.isPresent() && fruitsVariety.getId() != fruitsVarietyFromDb.get().getId()) {
			logger.warn("Fruits variety with title \"" + fruitsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createFruitsVariety(FruitsVariety fruitsVariety) {
		logger.trace("Adding new fruits variety to database...");
		
		if (checkIfExists(fruitsVariety))
			return false;

		logger.trace("Saving new fruits variety in database...");
		fruitsVarietyRepository.save(fruitsVariety);
		return true;
	}

	public boolean updateFruitsVariety(FruitsVariety fruitsVariety) {
		logger.trace("Updating fruits variety in database...");
		
		if (checkIfExists(fruitsVariety))
			return false;
		
		logger.trace("Saving updated fruits variety in database...");
		fruitsVarietyRepository.save(fruitsVariety);
		return true;
	}

	public void deleteFruitsVariety(FruitsVariety fruitsVariety) {
		logger.trace("Deleting fruits variety from database...");
		
		fruitsVarietyRepository.delete(fruitsVariety);
	}

	public List<FruitsVariety> mapFruitsVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping fruits varieties to domain objects...");
		
		List<FruitsVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			FruitsVariety fruitsVariety = new FruitsVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					fruitsVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					fruitsVariety.setName(entryValue);
				}
			}

			mappedList.add(fruitsVariety);
		}

		return mappedList;
	}
}
