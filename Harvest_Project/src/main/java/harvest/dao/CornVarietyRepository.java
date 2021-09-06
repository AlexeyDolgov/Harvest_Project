package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CornVariety;

public interface CornVarietyRepository extends JpaRepository<CornVariety, Integer> {

}
