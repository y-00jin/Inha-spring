package com.obj.meeting.dto;

import lombok.Data;

/**
 * @PackageName : com.obj.meeting.dto
 * @FileName : User
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
@Data
public class User {
    private Long id;
    private String username; // id 역할을 함
    private String password;
    private String email;
    private String role;
}
