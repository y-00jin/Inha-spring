package com.obj.meeting.service;

import com.obj.meeting.entity.Group;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName : com.obj.meeting.service
 * @FileName : GroupService
 * @Date : 9/16/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/16/24        y00jin       최초 생성
 */
@Service
public class GroupService {

    public Group getGroup(String groupName){
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupDescription("스프링부트 개발 세미나");
        group.setGroupImage("springboot.png");
        group.setMeetingAddress("서울");
        return group;
    }

    public String saveGroup(Group group){
        return group.toString();
    }

    public List<Group> getGroupList(){
        Group group = new Group();
        group.setGroupName("회의");
        group.setGroupDescription("스프링부트 개발 세미나");
        group.setGroupImage("springboot.png");
        group.setMeetingDate(Date.valueOf("2024-09-11"));
        group.setMeetingAddress("서울");

        Group group2 = new Group();
        group2.setGroupName("회의2");
        group2.setGroupDescription("스프링부트 개발 세미나2");
        group2.setGroupImage("springboot2.png");
        group2.setMeetingDate(Date.valueOf("2024-09-25"));
        group2.setMeetingAddress("서울2");

        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        groupList.add(group2);
        return groupList;
    }

    public String updateGroup(Group group){
        return "UPDATE " + group.toString();
    }

    public String deleteGroup(Group group){
        return "DELETE " + group.toString();
    }
}
