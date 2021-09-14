package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.GarlicVariety;

public interface GarlicVarietyRepository extends JpaRepository<GarlicVariety, Integer> {

	Optional<GarlicVariety> findByName(String name);

}
