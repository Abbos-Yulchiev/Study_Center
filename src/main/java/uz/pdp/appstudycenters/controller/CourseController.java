package uz.pdp.appstudycenters.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.payload.CourseDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //Create
    @PostMapping
    public Result addCourse(@RequestBody CourseDto courseDto){
        Result result = courseService.add(courseDto);
        return result;
    }

    //Read All
    @GetMapping
    public List<Course> getCourses(){
        List<Course> courses = courseService.getAll();
        return courses;
    }

    //Read One
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Integer id){
        Course course = courseService.getOne(id);
        return course;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateCourse(@RequestBody CourseDto courseDto, @PathVariable Integer id){
        Result result = courseService.update(id, courseDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable Integer id){
        Result result = courseService.delete(id);
        return result;
    }

}
