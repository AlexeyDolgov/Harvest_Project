package harvest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BeansVariety;

public interface BeansVarietyRepository extends JpaRepository<BeansVariety, Integer> {

	Optional<BeansVariety> findByName(String name);

}
