package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PumpkinsHarvesting;

public interface PumpkinsHarvestingRepository extends JpaRepository<PumpkinsHarvesting, Integer> {

}
