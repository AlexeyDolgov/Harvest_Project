package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.RootsHarvesting;

public interface RootsHarvestingRepository extends JpaRepository<RootsHarvesting, Integer> {

}
