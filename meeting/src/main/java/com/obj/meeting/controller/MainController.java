package com.obj.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/menu")
    public void main() {}

    @RequestMapping("/getUserList")
    public void getUserList() {}

    @RequestMapping("/getUser")
    public void getUser() {}

    @RequestMapping("/saveUser")
    public void saveUser() {}

    @RequestMapping("/getGroupList")
    public void getGroupList() {}

    @RequestMapping("/getGroup")
    public void getGroup() {}

    @RequestMapping("/saveGroup")
    public void saveGroup() {}
}
