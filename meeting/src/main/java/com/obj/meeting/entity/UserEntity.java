package com.obj.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @PackageName : com.obj.meeting.entity
 * @FileName : UserEntity
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = false, nullable = true, length = 255)
    private String password;

    @Column(unique = false, nullable = true, length = 50)
    private String email;

    @Column(unique = false, nullable = true, length = 50)
    private String role;
}
