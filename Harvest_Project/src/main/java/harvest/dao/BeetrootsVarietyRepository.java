package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BeetrootsVariety;

public interface BeetrootsVarietyRepository extends JpaRepository<BeetrootsVariety, Integer> {

	Optional<BeetrootsVariety> findByName(String name);

}
