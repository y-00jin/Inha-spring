package com.obj.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

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
}
