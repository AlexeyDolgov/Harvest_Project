package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CornVariety;

public interface CornVarietyRepository extends JpaRepository<CornVariety, Integer> {

	Optional<CornVariety> findByName(String name);

}
