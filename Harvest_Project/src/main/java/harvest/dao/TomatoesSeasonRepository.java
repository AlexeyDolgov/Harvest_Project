package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.TomatoesSeason;

public interface TomatoesSeasonRepository extends JpaRepository<TomatoesSeason, Integer> {

}
