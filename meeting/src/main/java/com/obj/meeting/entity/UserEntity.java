package com.obj.meeting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @PackageName : com.obj.meeting.entity
 * @FileName : UserEntity
 * @Date : 10/2/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        y00jin       최초 생성
 */
@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(unique = false, nullable = true, length = 255)
    private String password;
    @Column(unique = false, nullable = true, length = 50)
    private String email;
    @Column(unique = false, nullable = true, length = 50)
    private String role;
}




