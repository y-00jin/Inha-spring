package com.obj.meeting.controller;

import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser(UserEntity user){
        return userService.saveUser(user);
    }

    @GetMapping("/get/{username}")
    public UserEntity getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    @GetMapping("/list")
    public List<UserEntity> getUserList(){
        return userService.getUserList();
    }
}
