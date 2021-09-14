package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.CucumbersVarietyRepository;
import harvest.domain.CucumbersVariety;

@Service
public class CucumbersVarietyService {
	Logger logger = LoggerFactory.getLogger(CucumbersVarietyService.class);
	
	@Autowired
	private CucumbersVarietyRepository cucumbersVarietyRepository;

	public List<CucumbersVariety> findAll() {
		logger.trace("Getting all cucumbers varieties from database...");
		
		return cucumbersVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(CucumbersVariety cucumbersVariety) {
    	logger.trace("Checking if stored cucumbers variety already exists in database...");
		
		Optional<CucumbersVariety> cucumbersVarietyFromDb = cucumbersVarietyRepository.findByName(cucumbersVariety.getName());
	
		if (cucumbersVarietyFromDb.isPresent() && cucumbersVariety.getId() != cucumbersVarietyFromDb.get().getId()) {
			logger.warn("Cucumbers variety with title \"" + cucumbersVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createCucumbersVariety(CucumbersVariety cucumbersVariety) {
		logger.trace("Adding new cucumbers variety to database...");
		
		if (checkIfExists(cucumbersVariety))
			return false;

		logger.trace("Saving new cucumbers variety in database...");
		cucumbersVarietyRepository.save(cucumbersVariety);
		return true;
	}

	public boolean updateCucumbersVariety(CucumbersVariety cucumbersVariety) {
		logger.trace("Updating cucumbers variety in database...");
		
		if (checkIfExists(cucumbersVariety))
			return false;
		
		logger.trace("Saving updated cucumbers variety in database...");
		cucumbersVarietyRepository.save(cucumbersVariety);
		return true;
	}

	public void deleteCucumbersVariety(CucumbersVariety cucumbersVariety) {
		logger.trace("Deleting cucumbers variety from database...");
		
		cucumbersVarietyRepository.delete(cucumbersVariety);
	}
}
