package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CarrotsVariety;

public interface CarrotsVarietyRepository extends JpaRepository<CarrotsVariety, Integer> {
	
	Optional<CarrotsVariety> findByName(String name);

}
