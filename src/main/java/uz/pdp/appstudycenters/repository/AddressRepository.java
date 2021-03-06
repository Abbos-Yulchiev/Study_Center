package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByStreetNameAndCodeAndDistrictId(String streetName, String code, Integer district_id);


}
