package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appstudycenters.entity.Course;
import uz.pdp.appstudycenters.payload.Result;
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
    public List<Course> searchActiveCourse(@RequestParam("name") String name) {
        List<Course> courseList = mainService.search(name);
        return courseList;
    }

    @GetMapping("/byCourseNameAndRegionName")
    public List<Course> searchForRegionAndName(@RequestParam("name") String name, @RequestParam("region") String region) {
        List<Course> courseList = mainService.searchForRegionAndName(name, region);
        return courseList;
    }

    @GetMapping("/byCourseNameAndDistrictName")
    public List<Course> searchForDistrictAndName(@RequestParam("name") String name, @RequestParam("district") String district) {
        List<Course> courseList = mainService.searchForDistrictAndName(name, district);
        return courseList;
    }

    //District nomi bo'yicha Barcha kurslarni qidirish
    @GetMapping("/byDistrict")
    public List<Course> searchForDistrict(@RequestParam("district") String district) {
        List<Course> courseList = mainService.searchByDistrict(district);
        return courseList;
    }

    //COMPANIYA NOMINI BERIB COURSNI TOPISH
    @GetMapping("/byCompany")
    public Result searchForCompanyCours(@RequestParam("name") String companyName) {
        return mainService.searchCourseByCompanyName(companyName);
    }

    //COURS CATEGORIYASI NOMINI BERIB BARCHA COUSRLARNI TOPISH
    @GetMapping("/byCategoryName")
    public Result searchForCategoryName(@RequestParam("name") String name) {

        return mainService.searchCourseByCategoryName(name);
    }

    //Region nomi bo'yicha Barcha kurslarni qidirish
    @GetMapping("/byRegion")
    public List<Course> searchByRegionName(@RequestParam("region") String name) {

        return mainService.searchByRegionName(name );
    }

    //Sub_Category bo'yicha qidirish
    @GetMapping(value = "/bySubCategoryName")
    public List<Course> searchByCourseCategory(@RequestParam("name") String name) {

        return mainService.searchByCourseCategory(name);
    }
}
