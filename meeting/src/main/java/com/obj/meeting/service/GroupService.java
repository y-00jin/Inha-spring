package com.obj.meeting.service;

import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public String saveGroup(GroupEntity group){
        groupRepository.save(group);
        return "SUCCESS";
    }
    public GroupEntity getGroup(Long id){
        return groupRepository.findByGroupId(id);
    }

    public List<GroupEntity> getGroupList(){
        return groupRepository.findAll();
    }

    public String updateGroup(GroupEntity group){
        groupRepository.save(group);
        return "UPDATE SUCCESS";
    }
    public String deleteGroup(Long groupId){
        groupRepository.deleteById(groupId);
        return "DELETE SUCCESS";
    }

}
