package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PotatoesVariety;

public interface PotatoesVarietyRepository extends JpaRepository<PotatoesVariety, Integer> {

	Optional<PotatoesVariety> findByName(String name);

}
