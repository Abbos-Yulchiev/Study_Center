package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.ActiveCourse;

public interface ActiveCourseRepository extends JpaRepository<ActiveCourse, Integer> {
}
