package com.obj.meeting.controller;

import com.obj.meeting.entity.Group;
import com.obj.meeting.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PackageName : com.obj.meeting.controller
 * @FileName : GroupController
 * @Date : 9/16/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/16/24        y00jin       최초 생성
 */
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/get")
    public Group getGroup(String groupName){

        return groupService.getGroup(groupName);
    }

    @PostMapping("/save")
//    public String saveGroup(@RequestBody Group group){
    public String saveGroup(Group group){
        return groupService.saveGroup(group);
    }

    @GetMapping("/list")
    public List<Group> getListGroup(){
        return groupService.getGroupList();
    }

    @PutMapping("/update")
    public String updateGroup(@RequestBody Group group){
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestBody Group group){
        return groupService.deleteGroup(group);
    }

}
