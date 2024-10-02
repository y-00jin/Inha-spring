package com.obj.meeting.service;

import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String saveUser(UserEntity user) {
        userRepository.save(user);
        return "SUCCESS";
    }

    public UserEntity getUser(String username) {

        return userRepository.findByUsername(username);
    }

    public List<UserEntity> getUserList() {
        return userRepository.findAll();
    }

}
