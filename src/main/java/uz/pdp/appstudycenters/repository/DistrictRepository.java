package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    boolean existsByNameAndCodeAndRegion_Id(String name, String code, Integer region_id);

}
