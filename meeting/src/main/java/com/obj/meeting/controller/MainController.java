package com.obj.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PackageName : com.obj.meeting.controller
 * @FileName : MainController
 * @Date : 10/2/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        y00jin       최초 생성
 */
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
