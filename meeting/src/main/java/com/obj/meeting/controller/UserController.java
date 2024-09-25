package com.obj.meeting.controller;

import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @PackageName : com.obj.meeting.controller
 * @FileName : UserController
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public Optional<UserEntity> getUser(@PathVariable String id){
        return userService.getUser(id);
    }


    @PostMapping("/save")
    public String saveUser(UserEntity user){
        return userService.saveUser(user);
    }

    @GetMapping("/list")
    public List<UserEntity> listUser(){
        return userService.getUserList();
    }
}
