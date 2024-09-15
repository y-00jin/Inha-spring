package org.obj.test20240911.service;

import org.obj.test20240911.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    public User getUser() {
        User user = new User();
        user.setId("hong");
        user.setUsername("HongGilDong");
        user.setPassword("1234");
        user.setEmail("hong@gmail.com");
        user.setRole("admin");
        return user;
    }

    public List<User> getUserList() {
        User user = new User();
        user.setId("hong");
        user.setUsername("HongGilDong");
        user.setPassword("1234");
        user.setEmail("hong@gmail.com");
        user.setRole("admin");

        User user2 = new User();
        user2.setId("hong");
        user2.setUsername("HongGilDong");
        user2.setPassword("1234");
        user2.setEmail("hong@gmail.com");
        user2.setRole("admin");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        return userList;
    }

    public String saveUser(User user) {
        System.out.println(user.toString());
        return "save " + user.getUsername();
    }
}
