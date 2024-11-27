package com.obj.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="donation")
public class DonationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 기부 id

    @Column(unique = false, nullable = true)
    private String name;    //기부명

    @Column(unique = false, nullable = true)
    private String creator; // 기부 개설자id

    @Column(unique = false, nullable = true)
    private String description; // 기부 설명

    @Column(unique = false, nullable = true)
    private double donationAmount;  // 기부 금액

    @Column(unique = false, nullable = true)
    private double totalAmount;  // 기부 총액

    @Column(unique = false, nullable = true)
    private Date dueDate;   //기부 마감일자

    @Column(unique = false, nullable = true)
    private int donorCount; //기부자 수


    // user 객체와 n:n 관계. 조인테이블 구성
    @ManyToMany(fetch = FetchType.EAGER) // 조인 테이블 바로 구성해서 가져옴
    @JoinTable(name = "donation_user",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<UserEntity> donors = new ArrayList();

    // 참여자수 가져오기
    public int getDonorCount(){
        this.donorCount = donors.size();
        return this.donorCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDonorCount(int donorCount) {
        this.donorCount = donorCount;
    }

    public List<UserEntity> getDonors() {
        return donors;
    }

    public void setDonors(List<UserEntity> donors) {
        this.donors = donors;
    }



}
