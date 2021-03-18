package uz.pdp.appstudycenters.service;

import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.ActiveCourse;
import uz.pdp.appstudycenters.entity.Application;
import uz.pdp.appstudycenters.entity.User;
import uz.pdp.appstudycenters.payload.ApplicationDto;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.ActiveCourseRepository;
import uz.pdp.appstudycenters.repository.ApplicationRepository;
import uz.pdp.appstudycenters.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    final ApplicationRepository applicationRepository;
    final ActiveCourseRepository activeCourseRepository;
    final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, ActiveCourseRepository activeCourseRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.activeCourseRepository = activeCourseRepository;
        this.userRepository = userRepository;
    }

    public Result add(ApplicationDto applicationDto) {
        Application application = new Application();

        Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(applicationDto.getActiveCourseId());
        if (!optionalActiveCourse.isPresent()){
            return new Result("ActiveCourse id not found", false);
        }
        ActiveCourse activeCourse = optionalActiveCourse.get();
        application.setActiveCourse(activeCourse);

        Optional<User> optionalUser = userRepository.findById(applicationDto.getUserId());
        if (!optionalUser.isPresent()){
            return new Result("User id not found", false);
        }
        User user = optionalUser.get();
        application.setUser(user);

        return new Result("Application saved", true);
    }

    public Application getOne(Integer id) {

        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (!optionalApplication.isPresent()){
            return new Application();
        }
        Application application = optionalApplication.get();
        return application;
    }

    public List<Application> getAll() {
        List<Application> all = applicationRepository.findAll();
        return all;
    }

    public Result update(Integer id, ApplicationDto applicationDto) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (!optionalApplication.isPresent()){
            return new Result("Application id not found", false);
        }
        Application application = optionalApplication.get();

        Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(applicationDto.getActiveCourseId());
        if (!optionalActiveCourse.isPresent()){
            return new Result("ActiveCourse id not foun", false);
        }
        ActiveCourse activeCourse = optionalActiveCourse.get();
        application.setActiveCourse(activeCourse);

        Optional<User> optionalUser = userRepository.findById(applicationDto.getUserId());
        if (!optionalUser.isPresent()){
            return new Result("User id not found", false);
        }
        User user = optionalUser.get();
        application.setUser(user);

        applicationRepository.save(application);
        return new Result("Application updated", true);
    }


    public Result delete(Integer id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (!optionalApplication.isPresent()){
            return new Result("Application id not found", false);
        }
        applicationRepository.deleteById(id);
        return new Result("Application deleted", true);
    }
}
