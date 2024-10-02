package com.obj.meeting.controller;

import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/save")
    public String saveGroup(GroupEntity group){
        return groupService.saveGroup(group);
    }

    @GetMapping("/get/{id}")
    public GroupEntity getGroup(@PathVariable Long id){
        return groupService.getGroup(id);
    }

    @GetMapping("/list")
    public List<GroupEntity> getGroupList(){
        return groupService.getGroupList();
    }

    @PostMapping("/update")
    public String updateGroup(GroupEntity group){
        return groupService.updateGroup(group);
    }

    @PostMapping("/delete/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        return groupService.deleteGroup(groupId);
    }
}
