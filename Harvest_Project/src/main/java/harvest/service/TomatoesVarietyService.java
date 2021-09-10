package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.TomatoesVarietyRepository;
import harvest.domain.TomatoesVariety;

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
}
