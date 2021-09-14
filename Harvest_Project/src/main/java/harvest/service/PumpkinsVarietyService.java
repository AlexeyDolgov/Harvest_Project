package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.PumpkinsVarietyRepository;
import harvest.domain.PumpkinsVariety;

@Service
public class PumpkinsVarietyService {
	Logger logger = LoggerFactory.getLogger(PumpkinsVarietyService.class);
	
	@Autowired
	private PumpkinsVarietyRepository pumpkinsVarietyRepository;

	public List<PumpkinsVariety> findAll() {
		logger.trace("Getting all pumpkins varieties from database...");
		
		return pumpkinsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(PumpkinsVariety pumpkinsVariety) {
    	logger.trace("Checking if stored pumpkins variety already exists in database...");
		
		Optional<PumpkinsVariety> pumpkinsVarietyFromDb = pumpkinsVarietyRepository.findByName(pumpkinsVariety.getName());
	
		if (pumpkinsVarietyFromDb.isPresent() && pumpkinsVariety.getId() != pumpkinsVarietyFromDb.get().getId()) {
			logger.warn("Pumpkins variety with title \"" + pumpkinsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createPumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Adding new pumpkins variety to database...");
		
		if (checkIfExists(pumpkinsVariety))
			return false;

		logger.trace("Saving new pumpkins variety in database...");
		pumpkinsVarietyRepository.save(pumpkinsVariety);
		return true;
	}

	public boolean updatePumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Updating pumpkins variety in database...");
		
		if (checkIfExists(pumpkinsVariety))
			return false;
		
		logger.trace("Saving updated pumpkins variety in database...");
		pumpkinsVarietyRepository.save(pumpkinsVariety);
		return true;
	}

	public void deletePumpkinsVariety(PumpkinsVariety pumpkinsVariety) {
		logger.trace("Deleting pumpkins variety from database...");
		
		pumpkinsVarietyRepository.delete(pumpkinsVariety);
	}
}
