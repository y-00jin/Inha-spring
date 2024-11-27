package com.obj.meeting.service;

import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUser(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));     // 시큐리티로 암호화

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
