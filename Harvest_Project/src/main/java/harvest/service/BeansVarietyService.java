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

import harvest.dao.BeansVarietyRepository;
import harvest.domain.BeansVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class BeansVarietyService {
	Logger logger = LoggerFactory.getLogger(BeansVarietyService.class);
	
	@Autowired
	private BeansVarietyRepository beansVarietyRepository;

	public List<BeansVariety> findAll() {
		logger.trace("Getting all beans varieties from database...");
		
		return beansVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(BeansVariety beansVariety) {
    	logger.trace("Checking if stored beans variety already exists in database...");
		
		Optional<BeansVariety> beansVarietyFromDb = beansVarietyRepository.findByName(beansVariety.getName());
	
		if (beansVarietyFromDb.isPresent() && beansVariety.getId() != beansVarietyFromDb.get().getId()) {
			logger.warn("Beans variety with title \"" + beansVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createBeansVariety(BeansVariety beansVariety) {
		logger.trace("Adding new beans variety to database...");
		
		if (checkIfExists(beansVariety))
			return false;

		logger.trace("Saving new beans variety in database...");
		beansVarietyRepository.save(beansVariety);
		return true;
	}

	public boolean updateBeansVariety(BeansVariety beansVariety) {
		logger.trace("Updating beans variety in database...");
		
		if (checkIfExists(beansVariety))
			return false;
		
		logger.trace("Saving updated beans variety in database...");
		beansVarietyRepository.save(beansVariety);
		return true;
	}

	public void deleteBeansVariety(BeansVariety beansVariety) {
		logger.trace("Deleting beans variety from database...");
		
		beansVarietyRepository.delete(beansVariety);
	}

	public List<BeansVariety> mapBeansVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping beans varieties to domain objects...");
		
		List<BeansVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			BeansVariety beansVariety = new BeansVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					beansVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					beansVariety.setName(entryValue);
				}
			}

			mappedList.add(beansVariety);
		}

		return mappedList;
	}
}
