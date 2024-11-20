package com.obj.meeting.controller;

import com.obj.meeting.dto.RequestGroup;
import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.service.GroupService;
import com.obj.meeting.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;


//    @PostMapping("/save")
//    public String saveGroup(GroupEntity group){
//        return groupService.saveGroup(group);
//    }


    private Path location;

    @PostConstruct  // 생성자 이후에 한번 실행됨
    public void init(){
        this.location = Paths.get("/Users/y00jin/Documents/upload");   // 파일 저장할 위치
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveGroup(RequestGroup group, @RequestParam("groupImage")MultipartFile groupImage) throws IOException {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(group.getGroupName());
        groupEntity.setGroupDescription(group.getGroupDescription());
        groupEntity.setGroupManagerId(group.getGroupManagerId());
        groupEntity.setMeetingDate(group.getMeetingDate());
        groupEntity.setMeetingAddress(group.getMeetingAddress());


        String fileName = StringUtils.cleanPath(groupImage.getOriginalFilename());
        Path filePath = location.resolve(fileName).normalize();

        groupImage.transferTo(filePath);
        groupEntity.setGroupImage("/"+fileName);
        groupService.saveGroup(groupEntity);

        return ResponseEntity.ok("모임 등록이 완료되었습니다.");
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



    // 모임 참여 - 데이터 저장
    @Transactional
    @PostMapping("/join/{groupId}")
    public ResponseEntity<?> joinGroup(@PathVariable Long groupId, @RequestParam String username){
        GroupEntity group = groupService.getGroup(groupId); // groupId로 그룹 정보 가져오기
        UserEntity user = userService.getUser(username); // username으로 사용자 정보 가져오기

        // 참여자수가 없다면
        if(group.getParticipants() == null){
            // participants 객체 생성
            group.setParticipants(new ArrayList<>());
        }

        if(group.getParticipants().contains(user)){
            // 사용자가 이미 참여된 상태
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 참여하였습니다.");

            // 위와 같은 의미
            //return ResponseEntity.status(400).body("이미 참여하였습니다.");

            // 400 : 처리 x -> 오류는 아님
        } else {
            // 참여시킴
            group.getParticipants().add(user);
        }

        // 그룹 정보 업데이트
        groupService.saveGroup(group);

        // ok() : 200
        return ResponseEntity.ok("참여가 완료되었습니다.");
    }

    // 참여자 리스트 get
    @GetMapping("/get/{groupId}/participants")
    public ResponseEntity<List<UserEntity>> getGroupParticipants(@PathVariable Long groupId){
        GroupEntity group = groupService.getGroup(groupId); // groupId 가져오면 그룹 정보 가져오고
        List<UserEntity> participants = group.getParticipants(); // 그 그룹 정보의 참여자 가져와서
        return ResponseEntity.ok(participants); // 보내줌
    }
}
