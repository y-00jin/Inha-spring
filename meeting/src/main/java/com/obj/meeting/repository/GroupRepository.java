package com.obj.meeting.repository;

import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupId(Long groupId);
}
