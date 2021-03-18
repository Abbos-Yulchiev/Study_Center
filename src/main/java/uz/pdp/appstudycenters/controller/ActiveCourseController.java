package uz.pdp.appstudycenters.controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.ActiveCourse;
import uz.pdp.appstudycenters.payload.ActiveCourseDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.ActiveCourseService;

@RestController
@RequestMapping(value = "/activeCourse")
public class ActiveCourseController {
    
    
    final ActiveCourseService activeCourseService;

    public ActiveCourseController(ActiveCourseService activeCourseService) {
        this.activeCourseService = activeCourseService;
    }

    @PostMapping(value = "/upload")
    public Result addActiveCourse(@RequestBody ActiveCourseDTO activeCourseDTO){

        return activeCourseService.addActiveCourse(activeCourseDTO);
    }

    /*Get Active Course By Id*/
    @GetMapping(value = "/get/{activeCourseId}")
    public Result activeCourse(@PathVariable Integer activeCourseId){

        return activeCourseService.activeCourse(activeCourseId);
    }

    /*Get Active Course page*/
    @GetMapping(value = "/get")
    public Page<ActiveCourse> activeCoursePage(@RequestParam Integer page){

        return activeCourseService.activeCoursePage(page);
    }

    @PutMapping(value = "/edit/{activeCourseId}")
    public Result editActiveCourse(@PathVariable Integer activeCourseId, @RequestBody ActiveCourseDTO activeCourseDTO){

        return activeCourseService.editActiveCourse(activeCourseId,activeCourseDTO);
    }

    @DeleteMapping(value = "/delete/{activeCourseId}")
    public Result deleteActiveCourse(@PathVariable Integer activeCourseId){

        return activeCourseService.deleteActiveCourse(activeCourseId);
    }

}
