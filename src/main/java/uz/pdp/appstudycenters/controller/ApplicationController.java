package uz.pdp.appstudycenters.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Application;
import uz.pdp.appstudycenters.payload.ApplicationDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping(value = "application")
public class ApplicationController {

    final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //Create
    @PostMapping
    public Result addApplication(@RequestBody ApplicationDto applicationDto){
        Result result = applicationService.add(applicationDto);
        return result;
    }

    //Read One
    @GetMapping("/{id}")
    public Application getApplication(@PathVariable Integer id){
        Application application = applicationService.getOne(id);
        return application;
    }

    //Read All
    @GetMapping
    public List<Application> getApplications(){
        List<Application> all = applicationService.getAll();
        return all;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateApplication(@PathVariable Integer id, @RequestBody ApplicationDto applicationDto){
        Result result = applicationService.update(id, applicationDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteApplication(@PathVariable Integer id){
        Result result = applicationService.delete(id);
        return result;
    }

}
