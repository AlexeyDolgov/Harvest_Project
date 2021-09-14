package harvest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harvest.dao.CarrotsVarietyRepository;
import harvest.domain.CarrotsVariety;

@Service
public class CarrotsVarietyService {
	Logger logger = LoggerFactory.getLogger(CarrotsVarietyService.class);
	
	@Autowired
	private CarrotsVarietyRepository carrotsVarietyRepository;

	public List<CarrotsVariety> findAll() {
		logger.trace("Getting all carrots varieties from database...");
		
		return carrotsVarietyRepository.findAll();
	}
	
	public boolean checkIfExists(CarrotsVariety carrotsVariety) {
    	logger.trace("Checking if stored carrots variety already exists in database...");
		
		Optional<CarrotsVariety> carrotsVarietyFromDb = carrotsVarietyRepository.findByName(carrotsVariety.getName());
	
		if (carrotsVarietyFromDb.isPresent() && carrotsVariety.getId() != carrotsVarietyFromDb.get().getId()) {
			logger.warn("Carrots variety with title \"" + carrotsVarietyFromDb.get().getName() + "\" already exists in database...");
			return true;
		}
		return false;
	}
	
	public boolean createCarrotsVariety(CarrotsVariety carrotsVariety) {
		logger.trace("Adding new carrots variety to database...");
		
		if (checkIfExists(carrotsVariety))
			return false;

		logger.trace("Saving new carrots variety in database...");
		carrotsVarietyRepository.save(carrotsVariety);
		return true;
	}

	public boolean updateCarrotsVariety(CarrotsVariety carrotsVariety) {
		logger.trace("Updating carrots variety in database...");
		
		if (checkIfExists(carrotsVariety))
			return false;
		
		logger.trace("Saving updated carrots variety in database...");
		carrotsVarietyRepository.save(carrotsVariety);
		return true;
	}

	public void deleteCarrotsVariety(CarrotsVariety carrotsVariety) {
		logger.trace("Deleting carrots variety from database...");
		
		carrotsVarietyRepository.delete(carrotsVariety);
	}
}
