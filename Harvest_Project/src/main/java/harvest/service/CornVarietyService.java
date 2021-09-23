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

import harvest.dao.CornVarietyRepository;
import harvest.domain.CornVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class CornVarietyService {
	Logger logger = LoggerFactory.getLogger(CornVarietyService.class);
	
	@Autowired
	private CornVarietyRepository cornVarietyRepository;

	public List<CornVariety> findAll() {
		logger.trace("Getting all corn varieties from database...");
		
		return cornVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(CornVariety cornVariety) {
    	logger.trace("Checking if stored corn variety already exists in database...");
		
		Optional<CornVariety> cornVarietyFromDb = cornVarietyRepository.findByName(cornVariety.getName());
	
		if (cornVarietyFromDb.isPresent() && cornVariety.getId() != cornVarietyFromDb.get().getId()) {
			logger.warn("Corn variety with title \"" + cornVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createCornVariety(CornVariety cornVariety) {
		logger.trace("Adding new corn variety to database...");
		
		if (checkIfExists(cornVariety))
			return false;

		logger.trace("Saving new corn variety in database...");
		cornVarietyRepository.save(cornVariety);
		return true;
	}

	public boolean updateCornVariety(CornVariety cornVariety) {
		logger.trace("Updating corn variety in database...");
		
		if (checkIfExists(cornVariety))
			return false;
		
		logger.trace("Saving updated corn variety in database...");
		cornVarietyRepository.save(cornVariety);
		return true;
	}

	public void deleteCornVariety(CornVariety cornVariety) {
		logger.trace("Deleting corn variety from database...");
		
		cornVarietyRepository.delete(cornVariety);
	}

	public List<CornVariety> mapCornVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping corn varieties to domain objects...");
		
		List<CornVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			CornVariety cornVariety = new CornVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					cornVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					cornVariety.setName(entryValue);
				}
			}

			mappedList.add(cornVariety);
		}

		return mappedList;
	}
}
