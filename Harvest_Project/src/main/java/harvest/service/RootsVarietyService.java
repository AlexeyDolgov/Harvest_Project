package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.RootsVarietyRepository;
import harvest.domain.RootsVariety;

@Service
public class RootsVarietyService {
	Logger logger = LoggerFactory.getLogger(RootsVarietyService.class);
	
	@Autowired
	private RootsVarietyRepository rootsVarietyRepository;

	public List<RootsVariety> findAll() {
		logger.trace("Getting all roots varieties from database...");
		
		return rootsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(RootsVariety rootsVariety) {
    	logger.trace("Checking if stored roots variety already exists in database...");
		
		Optional<RootsVariety> rootsVarietyFromDb = rootsVarietyRepository.findByName(rootsVariety.getName());
	
		if (rootsVarietyFromDb.isPresent() && rootsVariety.getId() != rootsVarietyFromDb.get().getId()) {
			logger.warn("Roots variety with title \"" + rootsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createRootsVariety(RootsVariety rootsVariety) {
		logger.trace("Adding new roots variety to database...");
		
		if (checkIfExists(rootsVariety))
			return false;

		logger.trace("Saving new roots variety in database...");
		rootsVarietyRepository.save(rootsVariety);
		return true;
	}

	public boolean updateRootsVariety(RootsVariety rootsVariety) {
		logger.trace("Updating roots variety in database...");
		
		if (checkIfExists(rootsVariety))
			return false;
		
		logger.trace("Saving updated roots variety in database...");
		rootsVarietyRepository.save(rootsVariety);
		return true;
	}

	public void deleteRootsVariety(RootsVariety rootsVariety) {
		logger.trace("Deleting roots variety from database...");
		
		rootsVarietyRepository.delete(rootsVariety);
	}
}
