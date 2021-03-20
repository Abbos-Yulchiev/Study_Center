package uz.pdp.appstudycenters.service;

import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.ActiveCourse;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.ActiveCourseRepository;
import uz.pdp.appstudycenters.repository.CourseRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    final ActiveCourseRepository activeCourseRepository;
    final CourseRepository courseRepository;

    public MainService(ActiveCourseRepository activeCourseRepository, CourseRepository courseRepository) {
        this.activeCourseRepository = activeCourseRepository;
        this.courseRepository = courseRepository;
    }

    public List<Course> search(String name) {
        Optional<List<Course>> optionalCourseList = courseRepository.findAllByActiveId(name);
        if (!optionalCourseList.isPresent()){
            return new ArrayList<>();
        }
        List<Course> courseList = optionalCourseList.get();

        return courseList;
    }

    public List<Course> searchForRegionAndName(String name, String region) {
        Optional<List<Course>> optionalCourseList = courseRepository.findAllByNameAndRegion(name, region);
        if (!optionalCourseList.isPresent()){
            return new ArrayList<>();
        }
        List<Course> courses = optionalCourseList.get();
        return courses;
    }

    public List<Course> searchForDistrictAndName(String name, String district) {
        Optional<List<Course>> optionalCourseList = courseRepository.findAllByNameAndDistrict(name, district);
        if (!optionalCourseList.isPresent()){
            return new ArrayList<>();
        }
        List<Course> courses = optionalCourseList.get();
        return courses;
    }

    public List<Course> searchByDistrict(String district) {
        Optional<List<Course>> optionalCourseList = courseRepository.findAllCourseByDistrict(district);
        if (!optionalCourseList.isPresent()){
            return new ArrayList<>();
        }
        List<Course> courses = optionalCourseList.get();
        return courses;
    }

    public Result searchCourseByCompanyName(String companyName){
        List<Course> courseByCompany = courseRepository.getCourseByCompany(companyName);
        return new Result("Siz kiritgan companiyadagi Kurslar",true,courseByCompany);
    }
    public Result searchCourseByCategoryName(String categoryName){
        List<Course> courseByCategoryName = courseRepository.getCourseByCategoryName(categoryName);
        return new Result("Siz izlagan categoriya Bo'yicha Cousrlar",true,courseByCategoryName);
    }
}
