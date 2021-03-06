package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CabbageVariety;

public interface CabbageVarietyRepository extends JpaRepository<CabbageVariety, Integer> {

	Optional<CabbageVariety> findByName(String name);

}
