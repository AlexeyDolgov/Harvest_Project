package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.TomatoesVariety;

public interface TomatoesVarietyRepository extends JpaRepository<TomatoesVariety, Integer> {

}
