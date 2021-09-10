package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BerriesHarvesting;

public interface BerriesHarvestingRepository extends JpaRepository<BerriesHarvesting, Integer> {

}
