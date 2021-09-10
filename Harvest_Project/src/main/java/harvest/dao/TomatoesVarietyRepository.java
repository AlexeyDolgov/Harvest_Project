package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.TomatoesVariety;

public interface TomatoesVarietyRepository extends JpaRepository<TomatoesVariety, Integer> {

	Optional<TomatoesVariety> findByName(String name);

}
