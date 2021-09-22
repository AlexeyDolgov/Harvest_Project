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

import harvest.dao.TomatoesVarietyRepository;
import harvest.domain.TomatoesVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class TomatoesVarietyService {
	Logger logger = LoggerFactory.getLogger(TomatoesVarietyService.class);
	
	@Autowired
	private TomatoesVarietyRepository tomatoesVarietyRepository;

	public List<TomatoesVariety> findAll() {
		logger.trace("Getting all tomatoes varieties from database...");
		
		return tomatoesVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(TomatoesVariety tomatoesVariety) {
    	logger.trace("Checking if stored tomatoes variety already exists in database...");
		
		Optional<TomatoesVariety> tomatoesVarietyFromDb = tomatoesVarietyRepository.findByName(tomatoesVariety.getName());
	
		if (tomatoesVarietyFromDb.isPresent() && tomatoesVariety.getId() != tomatoesVarietyFromDb.get().getId()) {
			logger.warn("Tomatoes variety with title \"" + tomatoesVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createTomatoesVariety(TomatoesVariety tomatoesVariety) {
		logger.trace("Adding new tomatoes variety to database...");
		
		if (checkIfExists(tomatoesVariety))
			return false;

		logger.trace("Saving new tomatoes variety in database...");
		tomatoesVarietyRepository.save(tomatoesVariety);
		return true;
	}

	public boolean updateTomatoesVariety(TomatoesVariety tomatoesVariety) {
		logger.trace("Updating tomatoes variety in database...");
		
		if (checkIfExists(tomatoesVariety))
			return false;
		
		logger.trace("Saving updated tomatoes variety in database...");
		tomatoesVarietyRepository.save(tomatoesVariety);
		return true;
	}

	public void deleteTomatoesVariety(TomatoesVariety tomatoesVariety) {
		logger.trace("Deleting tomatoes variety from database...");
		
		tomatoesVarietyRepository.delete(tomatoesVariety);
	}
	
	public List<TomatoesVariety> mapTomatoesVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping tomatoes varieties to domain objects...");
		
		List<TomatoesVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			TomatoesVariety tomatoesVariety = new TomatoesVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					tomatoesVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					tomatoesVariety.setName(entryValue);
				}
			}

			mappedList.add(tomatoesVariety);
		}

		return mappedList;
	}
}
