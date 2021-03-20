package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.service.MainService;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/byCourseName")
    public List<Course> searchActiveCourse(@RequestParam("name") String name){
        List<Course> courseList = mainService.search(name);
        return courseList;
    }

    @GetMapping("/byCourseNameAndRegionName")
    public List<Course> searchForRegionAndName(@RequestParam("name") String name, @RequestParam("region") String region){
        List<Course> courseList = mainService.searchForRegionAndName(name, region);
        return courseList;
    }

    @GetMapping("/byCourseNameAndDistrictName")
    public List<Course> searchForDistrictAndName(@RequestParam("name") String name, @RequestParam("district") String district){
        List<Course> courseList = mainService.searchForDistrictAndName(name, district);
        return courseList;
    }

    @GetMapping("/byDistrict")
    public List<Course> searchForDistrict(@RequestParam("district") String district){
        List<Course> courseList = mainService.searchByDistrict(district);
        return courseList;
    }



}
