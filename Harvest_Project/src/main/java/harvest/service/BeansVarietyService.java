package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.BeansVarietyRepository;
import harvest.domain.BeansVariety;

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
}
