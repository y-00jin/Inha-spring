package com.obj.meeting.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @PackageName : com.obj.meeting.entity
 * @FileName : Group
 * @Date : 9/16/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/16/24        y00jin       최초 생성
 */
@Getter
@Setter
@ToString
public class Group {

    private Long groupId;
    private String groupName;
    private String groupDescription;
    private String groupImage;
    private Date meetingDate;
    private String meetingAddress;
}
