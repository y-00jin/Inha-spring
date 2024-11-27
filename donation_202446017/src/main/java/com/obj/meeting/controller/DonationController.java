package com.obj.meeting.controller;

import com.obj.meeting.dto.RequestGroup;
import com.obj.meeting.entity.DonationEntity;
import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.service.DonationService;
import com.obj.meeting.service.GroupService;
import com.obj.meeting.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<DonationEntity> getDonationList(){
        return donationService.getDonationList();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDonation(DonationEntity donationEntity) throws IOException {
        donationService.saveDonation(donationEntity);
        return ResponseEntity.ok("기부 등록이 완료되었습니다.");
    }

    @GetMapping("/get/{id}")
    public DonationEntity getDonation(@PathVariable Long id){
        return donationService.getDonation(id);
    }

    // 기부 참여 - 데이터 저장
    @Transactional
    @PostMapping("/join/{id}")
    public ResponseEntity<?> joinDonation(@PathVariable Long id, @RequestParam String username){
        DonationEntity donation = donationService.getDonation(id); // 기부 정보 가져오기
        UserEntity user = userService.getUser(username); // username으로 사용자 정보 가져오기

        // 참여자수가 없다면
        if(donation.getDonors() == null){
            // participants 객체 생성
            donation.setDonors(new ArrayList<>());
        }

        if(donation.getDonors().contains(user)){
            // 사용자가 이미 참여된 상태
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 기부하였습니다.");
        } else {
            // 참여시킴
            donation.getDonors().add(user);
        }

        donation.setDonorCount(donation.getDonorCount());   // 참여자 명
        donation.setTotalAmount(donation.getDonationAmount() * donation.getDonorCount());  //기부 총액

        // 그룹 정보 업데이트
        donationService.saveDonation(donation);

        // ok() : 200
        return ResponseEntity.ok("참여가 완료되었습니다.");
    }

    // 참여자 리스트 get
    @GetMapping("/get/{id}/donors")
    public ResponseEntity<List<UserEntity>> getDonationDonors(@PathVariable Long id){
        DonationEntity donation = donationService.getDonation(id); // groupId 가져오면 그룹 정보 가져오고
        List<UserEntity> donors = donation.getDonors(); // 그 그룹 정보의 참여자 가져와서
        return ResponseEntity.ok(donors); // 보내줌
    }

}
