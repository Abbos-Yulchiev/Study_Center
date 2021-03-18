package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.CourseCategory;
import uz.pdp.appstudycenters.payload.CourseCategoryDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.CourseCategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/courseCategory")
public class CourseCategoryController {

    final CourseCategoryService courseCategoryService;

    public CourseCategoryController(CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    //Create
    @PostMapping
    public Result addCourseCategory(@RequestBody CourseCategoryDto courseCategoryDto){
        Result result = courseCategoryService.add(courseCategoryDto);
        return result;
    }

    //Read All
    @GetMapping
    public List<CourseCategory> getCourseCategories(){
        List all = courseCategoryService.getAll();
        return all;
    }

    //Read One
    @GetMapping("/{id}")
    public CourseCategory getCourseCategory(@PathVariable Integer id){
        CourseCategory courseCategory = courseCategoryService.getOne(id);
        return courseCategory;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateCourseCategory(@PathVariable Integer id, @RequestBody CourseCategoryDto courseCategoryDto){
        Result result = courseCategoryService.update(id, courseCategoryDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteCourseCategory(@PathVariable Integer id){
        Result result = courseCategoryService.delete(id);
        return result;
    }

}
