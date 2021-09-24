package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.CabbageVarietySpecification;

public interface CabbageVarietySpecificationRepository extends JpaRepository<CabbageVarietySpecification, Integer> {

}
