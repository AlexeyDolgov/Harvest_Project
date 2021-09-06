package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.PotatoesHarvesting;

public interface PotatoesHarvestingRepository extends JpaRepository<PotatoesHarvesting, Integer> {

}
