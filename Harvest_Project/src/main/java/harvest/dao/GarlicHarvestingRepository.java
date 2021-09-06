package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.GarlicHarvesting;

public interface GarlicHarvestingRepository extends JpaRepository<GarlicHarvesting, Integer> {

}
