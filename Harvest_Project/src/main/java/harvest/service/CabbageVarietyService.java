package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.CabbageVarietyRepository;
import harvest.domain.CabbageVariety;

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
}
