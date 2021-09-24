package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PumpkinsSeason;

public interface PumpkinsSeasonRepository extends JpaRepository<PumpkinsSeason, Integer> {

}
