package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.TomatoesHarvesting;

public interface TomatoesHarvestingRepository extends JpaRepository<TomatoesHarvesting, Integer> {

}
