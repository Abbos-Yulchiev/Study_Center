package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
