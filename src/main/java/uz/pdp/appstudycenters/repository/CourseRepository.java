package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appstudycenters.entity.Course;

import java.util.List;
import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<List<Course>> findAllByName(String name);

    @Query(value = "select co.id, co.description, co.name, co.company_id from course co join active_course ac on co.id=ac.course_id where co.name=:name", nativeQuery = true)
    Optional<List<Course>> findAllByActiveId(String name);

    @Query(value = "select co.id, co.description, co.name, co.company_id from course co join company on company.id=co.company_id join address on company.address_id=address.id join district on district.id=address.district_id join region on region.id=district.region_id where region.name=:region and co.name=:name", nativeQuery = true)
    Optional<List<Course>> findAllByNameAndRegion(String name, String region);


}
