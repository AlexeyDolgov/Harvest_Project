package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BerriesSeason;

public interface BerriesSeasonRepository extends JpaRepository<BerriesSeason, Integer> {

}
