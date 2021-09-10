package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.SquashHarvesting;

public interface SquashHarvestingRepository extends JpaRepository<SquashHarvesting, Integer> {

}
