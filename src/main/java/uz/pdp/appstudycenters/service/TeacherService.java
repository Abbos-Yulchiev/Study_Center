package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appstudycenters.entity.Address;
import uz.pdp.appstudycenters.entity.Teacher;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.payload.TeacherDTO;
import uz.pdp.appstudycenters.repository.AddressRepository;
import uz.pdp.appstudycenters.repository.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService {


    final TeacherRepository teacherRepository;
    final AddressRepository addressRepository;

    public TeacherService(TeacherRepository teacherRepository, AddressRepository addressRepository) {
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
    }

    public Result addTeacher(@RequestBody TeacherDTO teacherDTO) {

        Optional<Address> optionalAddress = addressRepository.findById(teacherDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Invalid address Id.", false);

        Teacher teacher = new Teacher();

        teacher.setFirstname(teacherDTO.getFirstname());
        teacher.setLastname(teacherDTO.getLastname());
        teacher.setAge(teacherDTO.getAge());
        teacher.setContact(teacherDTO.getContact());
        teacher.setAddress(optionalAddress.get());
        teacher.setSpecialist(teacherDTO.getSpecialist());
        teacherRepository.save(teacher);
        return new Result("New Teacher successfully saved.", true, teacher.getId());
    }

    public Page<Teacher> getTeacherPage(@RequestParam("page") Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        return teacherRepository.findAll(pageable);
    }

    public Result getTeacher(@PathVariable Integer teacherId){

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (!optionalTeacher.isPresent())
            return new Result("Invalid teacher Id",false);

        return new Result("Teacher found",true,optionalTeacher);
    }
    public Result editTeacher(@PathVariable Integer teacherId, @RequestBody TeacherDTO teacherDTO){

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if(!optionalTeacher.isPresent())
            return new Result("Invalid teacher Id", false);

        Optional<Address> optionalAddress = addressRepository.findById(teacherDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Invalid teacher Id", false);

        Teacher teacher = optionalTeacher.get();
        teacher.setFirstname(teacherDTO.getFirstname());
        teacher.setLastname(teacherDTO.getLastname());
        teacher.setAge(teacherDTO.getAge());
        teacher.setContact(teacherDTO.getContact());
        teacher.setAddress(optionalAddress.get());
        teacher.setSpecialist(teacherDTO.getSpecialist());
        teacherRepository.save(teacher);
        return new Result("Teacher successfully edited.", true, teacher.getId());
    }
}
