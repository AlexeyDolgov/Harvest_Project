package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PotatoesVariety;

public interface PotatoesVarietyRepository extends JpaRepository<PotatoesVariety, Integer> {

}
