package com.obj.meeting.service;

import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @PackageName : com.obj.meeting.service
 * @FileName : UserService
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Optional<UserEntity> getUser(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    public String saveUser(UserEntity user) {
        userRepository.save(user);
        return "success";
    }

    public List<UserEntity> getUserList() {
        return userRepository.findAll();
    }
}
