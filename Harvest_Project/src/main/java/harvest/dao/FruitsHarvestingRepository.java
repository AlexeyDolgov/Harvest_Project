package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.FruitsHarvesting;

public interface FruitsHarvestingRepository extends JpaRepository<FruitsHarvesting, Integer> {

}
