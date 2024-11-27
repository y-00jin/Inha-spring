package com.obj.meeting.service;

import com.obj.meeting.entity.DonationEntity;
import com.obj.meeting.entity.GroupEntity;
import com.obj.meeting.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    public List<DonationEntity> getDonationList(){
        return donationRepository.findAll();
    }

    public String saveDonation(DonationEntity donation){
        donationRepository.save(donation);
        return "SUCCESS";
    }

    public DonationEntity getDonation(Long id){
        return donationRepository.findById(id).get();

    }
}
