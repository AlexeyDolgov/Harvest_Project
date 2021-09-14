package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PumpkinsVariety;

public interface PumpkinsVarietyRepository extends JpaRepository<PumpkinsVariety, Integer> {

	Optional<PumpkinsVariety> findByName(String name);

}
