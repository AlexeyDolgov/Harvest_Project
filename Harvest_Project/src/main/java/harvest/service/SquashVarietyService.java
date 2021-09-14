package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.SquashVarietyRepository;
import harvest.domain.SquashVariety;

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
}
