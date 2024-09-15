package org.obj.test20240911.controller;

import org.obj.test20240911.entity.User;
import org.obj.test20240911.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public User getUser() {
        return userService.getUser();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
