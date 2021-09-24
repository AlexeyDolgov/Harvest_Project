package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PotatoesSeason;

public interface PotatoesSeasonRepository extends JpaRepository<PotatoesSeason, Integer> {

}
