package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BeansHarvesting;

public interface BeansHarvestingRepository extends JpaRepository<BeansHarvesting, Integer> {

}
