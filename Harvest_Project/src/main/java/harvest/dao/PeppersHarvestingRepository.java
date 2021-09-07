package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PeppersHarvesting;

public interface PeppersHarvestingRepository extends JpaRepository<PeppersHarvesting, Integer> {

}
