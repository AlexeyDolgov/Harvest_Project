package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.CornVarietyRepository;
import harvest.domain.CornVariety;

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
}
