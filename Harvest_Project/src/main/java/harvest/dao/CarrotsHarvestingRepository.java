package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CarrotsHarvesting;

public interface CarrotsHarvestingRepository extends JpaRepository<CarrotsHarvesting, Integer> {

}
