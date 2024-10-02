package com.obj.meeting.repository;

import com.obj.meeting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
