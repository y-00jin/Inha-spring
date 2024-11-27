package com.obj.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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


    // 사용자가 어떤 그룹에 속해 있는지를 나타냄
    @ManyToMany(mappedBy = "participants")
    @JsonIgnore  // 양방으로 걸어두면 무한루프 발생할 수 있어서 json 구성 끊어둠
    private List<GroupEntity> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }
}




