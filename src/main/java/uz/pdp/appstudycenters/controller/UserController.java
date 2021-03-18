package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.payload.UserDTO;
import uz.pdp.appstudycenters.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/upload")
    public Result addUser(@RequestBody UserDTO userDTO) {

        return userService.addUser(userDTO);
    }


}
