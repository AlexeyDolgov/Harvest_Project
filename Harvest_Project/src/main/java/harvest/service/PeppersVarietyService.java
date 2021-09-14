package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.PeppersVarietyRepository;
import harvest.domain.PeppersVariety;

@Service
public class PeppersVarietyService {
	Logger logger = LoggerFactory.getLogger(PeppersVarietyService.class);
	
	@Autowired
	private PeppersVarietyRepository peppersVarietyRepository;

	public List<PeppersVariety> findAll() {
		logger.trace("Getting all peppers varieties from database...");
		
		return peppersVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(PeppersVariety peppersVariety) {
    	logger.trace("Checking if stored peppers variety already exists in database...");
		
		Optional<PeppersVariety> peppersVarietyFromDb = peppersVarietyRepository.findByName(peppersVariety.getName());
	
		if (peppersVarietyFromDb.isPresent() && peppersVariety.getId() != peppersVarietyFromDb.get().getId()) {
			logger.warn("Peppers variety with title \"" + peppersVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createPeppersVariety(PeppersVariety peppersVariety) {
		logger.trace("Adding new peppers variety to database...");
		
		if (checkIfExists(peppersVariety))
			return false;

		logger.trace("Saving new peppers variety in database...");
		peppersVarietyRepository.save(peppersVariety);
		return true;
	}

	public boolean updatePeppersVariety(PeppersVariety peppersVariety) {
		logger.trace("Updating peppers variety in database...");
		
		if (checkIfExists(peppersVariety))
			return false;
		
		logger.trace("Saving updated peppers variety in database...");
		peppersVarietyRepository.save(peppersVariety);
		return true;
	}

	public void deletePeppersVariety(PeppersVariety peppersVariety) {
		logger.trace("Deleting peppers variety from database...");
		
		peppersVarietyRepository.delete(peppersVariety);
	}
}
