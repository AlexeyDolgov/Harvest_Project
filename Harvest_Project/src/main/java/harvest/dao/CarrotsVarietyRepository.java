package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CarrotsVariety;

public interface CarrotsVarietyRepository extends JpaRepository<CarrotsVariety, Integer> {

}
