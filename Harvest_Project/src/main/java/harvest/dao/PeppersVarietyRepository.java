package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PeppersVariety;

public interface PeppersVarietyRepository extends JpaRepository<PeppersVariety, Integer> {
	
	Optional<PeppersVariety> findByName(String name);

}
