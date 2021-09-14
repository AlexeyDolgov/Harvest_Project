package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BerriesVariety;

public interface BerriesVarietyRepository extends JpaRepository<BerriesVariety, Integer> {

	Optional<BerriesVariety> findByName(String name);

}
