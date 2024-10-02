package com.obj.meeting.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Group {
    private Long groupId;
    private String groupName;
    private String groupManagerId;
    private String groupDescription;
    private String groupImage;
    private Date meetingDate;
    private String meetingAddress;
    private int participantCount;
}
