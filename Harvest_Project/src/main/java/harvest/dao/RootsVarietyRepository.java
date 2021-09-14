package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.RootsVariety;

public interface RootsVarietyRepository extends JpaRepository<RootsVariety, Integer> {

	Optional<RootsVariety> findByName(String name);

}
