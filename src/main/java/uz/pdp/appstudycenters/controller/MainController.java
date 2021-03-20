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

    @GetMapping
    public List<Course> searchActiveCourse(@RequestParam("name") String name){
        List<Course> courseList = mainService.search(name);
        return courseList;
    }

//    @GetMapping
//    public List<Course> searchForegionAndName(@RequestParam("name") String name, @RequestParam("region") String region){
////        List<Course> courseList = mainService.searchForegionAndName(name, region);
//        return courseList;
//    }



}
