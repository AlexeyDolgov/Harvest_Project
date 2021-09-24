package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CarrotsSeason;

public interface CarrotsSeasonRepository extends JpaRepository<CarrotsSeason, Integer> {

}
