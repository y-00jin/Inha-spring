package com.ex.test20240904;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 페이지 리턴
public class TestController2 {
    @GetMapping("/test2")
    public String test(){
        return "hello.html";
    }
}
