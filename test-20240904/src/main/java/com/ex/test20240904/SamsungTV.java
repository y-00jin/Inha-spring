package com.ex.test20240904;

public class SamsungTV implements TV{

    // 유지보수하기 위한 코드 추가
    public void powerOn() {
        System.out.println("SamsungTV -- 전원을 켠다.");
    }

    public void powerOff() {
        System.out.println("SamsungTV -- 전원을 끈다.");
    }
}
