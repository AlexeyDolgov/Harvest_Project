package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CabbageHarvesting;

public interface CabbageHarvestingRepository extends JpaRepository<CabbageHarvesting, Integer> {

}
