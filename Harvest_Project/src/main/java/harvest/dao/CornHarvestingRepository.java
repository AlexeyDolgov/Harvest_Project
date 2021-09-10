package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CornHarvesting;

public interface CornHarvestingRepository extends JpaRepository<CornHarvesting, Integer> {

}
