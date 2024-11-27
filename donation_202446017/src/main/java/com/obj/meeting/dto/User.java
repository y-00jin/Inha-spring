package com.obj.meeting.dto;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String role;
}
