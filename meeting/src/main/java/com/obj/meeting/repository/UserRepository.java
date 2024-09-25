package com.obj.meeting.repository;

import com.obj.meeting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @PackageName : com.obj.meeting.repository
 * @FileName : UserRepository
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 재정의
    @Override
    Optional<UserEntity> findById(Long id);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

}
