package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CabbageVariety;

public interface CabbageVarietyRepository extends JpaRepository<CabbageVariety, Integer> {

}
