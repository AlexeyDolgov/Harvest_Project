package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.SquashSeason;

public interface SquashSeasonRepository extends JpaRepository<SquashSeason, Integer> {

}
