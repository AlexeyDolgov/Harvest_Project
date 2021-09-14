package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.SquashVariety;

public interface SquashVarietyRepository extends JpaRepository<SquashVariety, Integer> {

	Optional<SquashVariety> findByName(String name);
	
}
