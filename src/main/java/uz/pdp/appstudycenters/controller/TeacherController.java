package uz.pdp.appstudycenters.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Teacher;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.payload.TeacherDTO;
import uz.pdp.appstudycenters.service.TeacherService;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/upload")
    public Result addTeacher(@RequestBody TeacherDTO teacherDTO){

        return teacherService.addTeacher(teacherDTO);
    }
    
    /*Get teacher by Id*/
    @GetMapping(value = "/get/{teacherId}")
    public Result getTeacher(@PathVariable Integer teacherId){

        return teacherService.getTeacher(teacherId);
    }
    
    /*GEt Teacher page*/
    @GetMapping(value = "/get")
    public Page<Teacher> getTeacherPage(@RequestParam("page") Integer page){

        return teacherService.getTeacherPage(page);
    }

    @PutMapping(value = "/edit/{teacherId}")
    public Result editTeacher(@PathVariable Integer teacherId, @RequestBody TeacherDTO teacherDTO){

        return teacherService.editTeacher(teacherId, teacherDTO);
    }
}
