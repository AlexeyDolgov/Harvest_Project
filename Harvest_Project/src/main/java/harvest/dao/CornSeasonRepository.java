package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CornSeason;

public interface CornSeasonRepository extends JpaRepository<CornSeason, Integer> {

}
