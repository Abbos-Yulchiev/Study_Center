package uz.pdp.appstudycenters.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.User;
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

    /*Get user by id*/

    @GetMapping(value = "/get/{userId}")
    public Result getUser(@PathVariable Integer userId){

        return userService.getUser(userId);
    }

    /*Getting user page*/
    @GetMapping(value = "/get")
    public Page<User> getUsers(@RequestParam("page") Integer page){

        return userService.getUsers(page);
    }

    @PutMapping(value = "/edit/{userId}")
    public Result editUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO){

        return userService.editUser(userId, userDTO);
    }

    @RequestMapping(value = "/delete/{userId}")
    public Result deleteUser(@PathVariable Integer userId){

        return userService.deleteUser(userId);
    }


}
