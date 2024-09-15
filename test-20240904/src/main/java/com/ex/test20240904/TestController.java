package com.ex.test20240904;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // 객체 리턴
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String test(@RequestBody User user){
        System.out.println(user.getName());
        return "<HELLO> " + user.getName();
    }

    @GetMapping("/get2")
    public String test2(String name){
        return "<Hello2> " + name;
    }

    @GetMapping("/get3")
    public String test3(){
        return "hello.html";
    }
}
