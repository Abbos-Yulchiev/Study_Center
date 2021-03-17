package uz.pdp.appstudycenters.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.CourseCategory;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {
}
