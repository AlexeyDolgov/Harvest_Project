package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CabbageSeason;

public interface CabbageSeasonRepository extends JpaRepository<CabbageSeason, Integer> {

}
