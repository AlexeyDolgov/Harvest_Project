package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.FruitsVariety;

public interface FruitsVarietyRepository extends JpaRepository<FruitsVariety, Integer> {

	Optional<FruitsVariety> findByName(String name);

}
