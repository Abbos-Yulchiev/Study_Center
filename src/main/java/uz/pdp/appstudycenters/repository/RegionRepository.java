package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    boolean existsByName(String name);
}
