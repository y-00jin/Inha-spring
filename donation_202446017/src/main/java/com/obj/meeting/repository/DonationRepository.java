package com.obj.meeting.repository;

import com.obj.meeting.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

}
