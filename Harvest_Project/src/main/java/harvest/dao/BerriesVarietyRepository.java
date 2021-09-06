package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.BerriesVariety;

public interface BerriesVarietyRepository extends JpaRepository<BerriesVariety, Integer> {

}
