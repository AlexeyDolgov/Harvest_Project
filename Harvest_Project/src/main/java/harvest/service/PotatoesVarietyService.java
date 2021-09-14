package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.PotatoesVarietyRepository;
import harvest.domain.PotatoesVariety;

@Service
public class PotatoesVarietyService {
	Logger logger = LoggerFactory.getLogger(PotatoesVarietyService.class);
	
	@Autowired
	private PotatoesVarietyRepository potatoesVarietyRepository;

	public List<PotatoesVariety> findAll() {
		logger.trace("Getting all potatoes varieties from database...");
		
		return potatoesVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(PotatoesVariety potatoesVariety) {
    	logger.trace("Checking if stored potatoes variety already exists in database...");
		
		Optional<PotatoesVariety> potatoesVarietyFromDb = potatoesVarietyRepository.findByName(potatoesVariety.getName());
	
		if (potatoesVarietyFromDb.isPresent() && potatoesVariety.getId() != potatoesVarietyFromDb.get().getId()) {
			logger.warn("Potatoes variety with title \"" + potatoesVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createPotatoesVariety(PotatoesVariety potatoesVariety) {
		logger.trace("Adding new potatoes variety to database...");
		
		if (checkIfExists(potatoesVariety))
			return false;

		logger.trace("Saving new potatoes variety in database...");
		potatoesVarietyRepository.save(potatoesVariety);
		return true;
	}

	public boolean updatePotatoesVariety(PotatoesVariety potatoesVariety) {
		logger.trace("Updating potatoes variety in database...");
		
		if (checkIfExists(potatoesVariety))
			return false;
		
		logger.trace("Saving updated potatoes variety in database...");
		potatoesVarietyRepository.save(potatoesVariety);
		return true;
	}

	public void deletePotatoesVariety(PotatoesVariety potatoesVariety) {
		logger.trace("Deleting potatoes variety from database...");
		
		potatoesVarietyRepository.delete(potatoesVariety);
	}
}
