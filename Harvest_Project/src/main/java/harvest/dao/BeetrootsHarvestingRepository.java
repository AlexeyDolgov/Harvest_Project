package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BeetrootsHarvesting;

public interface BeetrootsHarvestingRepository extends JpaRepository<BeetrootsHarvesting, Integer> {

}
