package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.CompanyCategory;

public interface CompanyCategoryRepository extends JpaRepository<CompanyCategory, Integer> {
}
