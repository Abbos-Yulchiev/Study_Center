package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appstudycenters.entity.ActiveCourse;
import uz.pdp.appstudycenters.entity.Course;

import java.util.List;

public interface ActiveCourseRepository extends JpaRepository<ActiveCourse, Integer> {

//    @Query("select * from active_course ")
//    List<Course> findAllByCourse_Name(String name);


}
