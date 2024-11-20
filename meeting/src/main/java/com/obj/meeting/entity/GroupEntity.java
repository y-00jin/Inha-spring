package com.obj.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="groupdetails")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    @Column(unique = false, nullable = true)
    private String groupName;
    @Column(unique = false, nullable = true)
    private String groupManagerId;
    @Column(unique = false, nullable = true)
    private String groupDescription;
    @Column(unique = false, nullable = true)
    private String groupImage;
    @Column(unique = false, nullable = true)
    private Date meetingDate;
    @Column(unique = false, nullable = true)
    private String meetingAddress;
    @Column(unique = false, nullable = true)
    private int participantCount;


    // user 객체와 n:n 관계. 조인테이블 구성
    @ManyToMany(fetch = FetchType.EAGER) // 조인 테이블 바로 구성해서 가져옴
    @JoinTable(name = "group_user",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<UserEntity> participants = new ArrayList();

    // 참여자수 가져오기
    public int getParticipantCount(){
        this.participantCount = participants.size();
        return this.participantCount;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupManagerId() {
        return groupManagerId;
    }

    public void setGroupManagerId(String groupManagerId) {
        this.groupManagerId = groupManagerId;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }
}
