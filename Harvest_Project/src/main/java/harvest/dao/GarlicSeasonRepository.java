package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.GarlicSeason;

public interface GarlicSeasonRepository extends JpaRepository<GarlicSeason, Integer> {

}
