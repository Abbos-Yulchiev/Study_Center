package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
