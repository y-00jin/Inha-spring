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
// 1. 화면 jsp의 submit을 통해 보낼 경우
//  public String saveUser(User user) { return userService.saveUser(user); }
// 2. json 형식으로 보낼 경우 (postman)
    public String saveUser(@RequestBody User user) { return userService.saveUser(user); }

    @GetMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}


