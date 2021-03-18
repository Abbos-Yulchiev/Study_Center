package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);
}
