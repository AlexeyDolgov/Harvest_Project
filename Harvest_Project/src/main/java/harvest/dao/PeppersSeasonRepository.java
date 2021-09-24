package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PeppersSeason;

public interface PeppersSeasonRepository extends JpaRepository<PeppersSeason, Integer> {

}
