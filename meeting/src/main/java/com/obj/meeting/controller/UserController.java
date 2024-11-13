package com.obj.meeting.controller;

import com.obj.meeting.dto.MeetingUserDetails;
import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser(UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get/{username}")
    public UserEntity getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @GetMapping("/list")
    public List<UserEntity> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/get/groupManagerId")
    public UserEntity getUserGroupManagerId() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setRole(user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""));
        return userEntity;
    }
}
