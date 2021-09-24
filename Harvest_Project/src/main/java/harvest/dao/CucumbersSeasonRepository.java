package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CucumbersSeason;

public interface CucumbersSeasonRepository extends JpaRepository<CucumbersSeason, Integer> {

}
