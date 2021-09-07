package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CucumbersHarvesting;

public interface CucumbersHarvestingRepository extends JpaRepository<CucumbersHarvesting, Integer> {

}
