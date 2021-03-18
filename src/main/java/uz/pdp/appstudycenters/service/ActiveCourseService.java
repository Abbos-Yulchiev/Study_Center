package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appstudycenters.entity.ActiveCourse;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.entity.User;
import uz.pdp.appstudycenters.payload.ActiveCourseDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.ActiveCourseRepository;
import uz.pdp.appstudycenters.repository.CourseRepository;
import uz.pdp.appstudycenters.repository.UserRepository;

import java.util.Optional;

@Service
public class ActiveCourseService {

    final ActiveCourseRepository activeCourseRepository;
    final CourseRepository courseRepository;
    final UserRepository userRepository;

    public ActiveCourseService(ActiveCourseRepository activeCourseRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.activeCourseRepository = activeCourseRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Result addActiveCourse(@RequestBody ActiveCourseDTO activeCourseDTO) {

        Optional<Course> optionalCourse = courseRepository.findById(activeCourseDTO.getCourseId());
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);

        for (User user : activeCourseDTO.getUser()) {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if (!optionalUser.isPresent())
                return new Result("Invalid User Id", false);
        }
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setCourse(optionalCourse.get());
        activeCourse.setMaxStudent(activeCourseDTO.getMaxStudent());
        activeCourse.setUser(activeCourseDTO.getUser());
        activeCourse.setDate(activeCourseDTO.getDate());
        activeCourseRepository.save(activeCourse);
        return new Result("New Active Course saved.", true, activeCourse.getId());
    }

    public Page<ActiveCourse> activeCoursePage(@RequestParam Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        return activeCourseRepository.findAll(pageable);
    }

    public Result activeCourse(Integer activeCourseId) {

        Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(activeCourseId);
        if (!optionalActiveCourse.isPresent())
            return new Result("Invalid Active course id", false);
        return new Result("Active Course", true, optionalActiveCourse);
    }

    public Result editActiveCourse(@PathVariable Integer activeCourseId, @RequestBody ActiveCourseDTO activeCourseDTO) {

        Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(activeCourseId);
        if (!optionalActiveCourse.isPresent())
            return new Result("Invalid ActiveCourse Id", false);

        Optional<Course> optionalCourse = courseRepository.findById(activeCourseDTO.getCourseId());
        if (!optionalCourse.isPresent())
            return new Result("Invalid Course Id", false);

        for (User user : activeCourseDTO.getUser()) {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if (!optionalUser.isPresent())
                return new Result("Invalid User Id", false);
        }

        ActiveCourse activeCourse = optionalActiveCourse.get();
        activeCourse.setCourse(optionalCourse.get());
        activeCourse.setMaxStudent(activeCourseDTO.getMaxStudent());
        activeCourse.setUser(activeCourseDTO.getUser());
        activeCourse.setDate(activeCourseDTO.getDate());
        activeCourseRepository.save(activeCourse);
        return new Result("Active Course edited.", true, activeCourse.getId());
    }

    public Result deleteActiveCourse(@PathVariable Integer activeCourseId){

        Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(activeCourseId);
        if (!optionalActiveCourse.isPresent())
            return new Result("Invalid Active Course Id", false);

        activeCourseRepository.deleteById(activeCourseId);
        return new Result("Active Course deleted!" ,true);
    }

}
