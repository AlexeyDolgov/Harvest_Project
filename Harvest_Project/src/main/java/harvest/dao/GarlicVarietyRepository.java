package harvest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import harvest.domain.GarlicVariety;

public interface GarlicVarietyRepository extends JpaRepository<GarlicVariety, Integer> {

}
