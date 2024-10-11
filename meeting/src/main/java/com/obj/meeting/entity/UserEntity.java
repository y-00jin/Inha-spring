package com.obj.meeting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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




