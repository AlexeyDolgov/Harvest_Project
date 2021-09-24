package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.FruitsSeason;

public interface FruitsSeasonRepository extends JpaRepository<FruitsSeason, Integer> {

}
