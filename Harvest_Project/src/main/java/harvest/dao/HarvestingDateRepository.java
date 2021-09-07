package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.HarvestingDate;

public interface HarvestingDateRepository extends JpaRepository<HarvestingDate, Integer> {

}
