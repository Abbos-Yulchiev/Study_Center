package uz.pdp.appstudycenters.service;

import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.Company;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.payload.CourseDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.CompanyRepository;
import uz.pdp.appstudycenters.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    final CourseRepository courseRepository;
    final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }

    public Result add(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());

        Optional<Company> optionalCompany = companyRepository.findById(courseDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new Result("Compnay id not found", false);
        }
        Company company = optionalCompany.get();
        course.setCompany(company);

        courseRepository.save(course);

        return new Result("Course saved", true);
    }

    public List<Course> getAll() {
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    public Course getOne(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            return new Course();
        }
        Course course = optionalCourse.get();
        return course;
    }

    public Result update(Integer id, CourseDto courseDto) {

        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            return new Result("Course id not found", false);
        }
        Course course = optionalCourse.get();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());

        Optional<Company> optionalCompany = companyRepository.findById(courseDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new Result("Company id not found", false);
        }
        Company company = optionalCompany.get();
        course.setCompany(company);

        courseRepository.save(course);
        return new Result("Course updated", true);
    }

    public Result delete(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            return new Result("Result id not found", false);
        }
        courseRepository.deleteById(id);
        return new Result("Course deleted", true);
    }
}
