package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CucumbersVariety;

public interface CucumbersVarietyRepository extends JpaRepository<CucumbersVariety, Integer> {

	Optional<CucumbersVariety> findByName(String name);

}
