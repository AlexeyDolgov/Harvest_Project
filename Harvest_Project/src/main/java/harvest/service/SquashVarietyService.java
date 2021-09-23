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

import harvest.dao.SquashVarietyRepository;
import harvest.domain.SquashVariety;
import harvest.dto.ImportVarietyFields;

@Service
public class SquashVarietyService {
	Logger logger = LoggerFactory.getLogger(SquashVarietyService.class);
	
	@Autowired
	private SquashVarietyRepository squashVarietyRepository;

	public List<SquashVariety> findAll() {
		logger.trace("Getting all squash varieties from database...");
		
		return squashVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(SquashVariety squashVariety) {
    	logger.trace("Checking if stored squash variety already exists in database...");
		
		Optional<SquashVariety> squashVarietyFromDb = squashVarietyRepository.findByName(squashVariety.getName());
	
		if (squashVarietyFromDb.isPresent() && squashVariety.getId() != squashVarietyFromDb.get().getId()) {
			logger.warn("Squash variety with title \"" + squashVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createSquashVariety(SquashVariety squashVariety) {
		logger.trace("Adding new squash variety to database...");
		
		if (checkIfExists(squashVariety))
			return false;

		logger.trace("Saving new squash variety in database...");
		squashVarietyRepository.save(squashVariety);
		return true;
	}

	public boolean updateSquashVariety(SquashVariety squashVariety) {
		logger.trace("Updating squash variety in database...");
		
		if (checkIfExists(squashVariety))
			return false;
		
		logger.trace("Saving updated squash variety in database...");
		squashVarietyRepository.save(squashVariety);
		return true;
	}

	public void deleteSquashVariety(SquashVariety squashVariety) {
		logger.trace("Deleting squash variety from database...");
		
		squashVarietyRepository.delete(squashVariety);
	}

	public List<SquashVariety> mapSquashVarietyFromExcelList(List<Map<Integer, String>> list,
			ImportVarietyFields importVarietyFields) {
		logger.trace("Mapping squash varieties to domain objects...");
		
		List<SquashVariety> mappedList = new ArrayList<>();

		for (Map<Integer, String> map : list) {
			SquashVariety squashVariety = new SquashVariety();

			for (Entry<Integer, String> entry : map.entrySet()) {

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("id"))) {
					Integer entryValue = Double.valueOf(entry.getValue()).intValue();
					squashVariety.setId(entryValue);
				}

				if (entry.getKey().equals(importVarietyFields.getColumnFields().get("name"))) {
					String entryValue = (String) entry.getValue();
					squashVariety.setName(entryValue);
				}
			}

			mappedList.add(squashVariety);
		}

		return mappedList;
	}
}
