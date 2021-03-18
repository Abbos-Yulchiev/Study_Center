package uz.pdp.appstudycenters.service;

import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.entity.CourseCategory;
import uz.pdp.appstudycenters.payload.CourseCategoryDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.CourseCategoryRepository;
import uz.pdp.appstudycenters.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseCategoryService {

    final CourseCategoryRepository courseCategoryRepository;
    final CourseRepository courseRepository;


    public CourseCategoryService(CourseCategoryRepository courseCategoryRepository, CourseRepository courseRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
        this.courseRepository = courseRepository;
    }

    public Result add(CourseCategoryDto courseCategoryDto) {
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setName(courseCategoryDto.getName());
        courseCategory.setDescription(courseCategoryDto.getDescription());

        Optional<Course> optionalCourse = courseRepository.findById(courseCategoryDto.getCourseId());
        if (!optionalCourse.isPresent()){
            return new Result("Course id not found",false);
        }
        Course course = optionalCourse.get();
        courseCategory.setCourse(course);

        Optional<CourseCategory> optionalCourseCategory = courseCategoryRepository.findById(courseCategoryDto.getCourseId());
        CourseCategory courseCategory1 = optionalCourseCategory.get();
        courseCategory.setCourseCategory(courseCategory1);

        courseCategoryRepository.save(courseCategory);
        return new Result("CourseCategory saved", true);
    }

    public List getAll() {
        List<CourseCategory> all = courseCategoryRepository.findAll();
        return all;
    }

    public CourseCategory getOne(Integer id) {
        Optional<CourseCategory> optionalCourseCategory = courseCategoryRepository.findById(id);
        if (!optionalCourseCategory.isPresent()){
            return new CourseCategory();
        }
        CourseCategory courseCategory = optionalCourseCategory.get();
        return courseCategory;
    }

    public Result update(Integer id, CourseCategoryDto courseCategoryDto) {
        Optional<CourseCategory> optionalCourseCategory = courseCategoryRepository.findById(id);
        if (!optionalCourseCategory.isPresent()){
            return new Result("CourseCategory id not found", false);
        }
        CourseCategory courseCategory = optionalCourseCategory.get();
        courseCategory.setName(courseCategoryDto.getName());
        courseCategory.setDescription(courseCategoryDto.getDescription());

        Optional<Course> optionalCourse = courseRepository.findById(courseCategoryDto.getCourseId());
        if (!optionalCourse.isPresent()){
            return new Result("Course id not found", false);
        }
        Course course = optionalCourse.get();
        courseCategory.setCourse(course);

        Optional<CourseCategory> courseCategoryOptional = courseCategoryRepository.findById(courseCategoryDto.getCourseCategoryId());
        if (!courseCategoryOptional.isPresent()){
            return new Result("Course category id not found", false);
        }
        CourseCategory courseCategory1 = courseCategoryOptional.get();
        courseCategory.setCourseCategory(courseCategory1);

        courseCategoryRepository.save(courseCategory);

        return new Result("CourseCategory updated", true);
    }

    public Result delete(Integer id) {
        Optional<CourseCategory> optionalCourseCategory = courseCategoryRepository.findById(id);
        if (!optionalCourseCategory.isPresent()){
            return new Result("CourseCategory id not found", false);
        }
        courseCategoryRepository.deleteById(id);
        return new Result("Course category deleted", true);
    }
}
