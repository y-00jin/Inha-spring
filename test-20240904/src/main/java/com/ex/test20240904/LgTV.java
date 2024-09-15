package com.ex.test20240904;

/**
 * description    :
 * packageName    : com.ex.test20240904
 * fileName        : LgTV
 * author         : kimminsol
 * date           : 9/4/24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/4/24        kimminsol       최초 생성
 */
public class LgTV implements TV {
    /*
    public void turnOn(){
        System.out.println("LgTV -- 전원을 켠다.");
    }

    public void turnOff(){
        System.out.println("LgTV -- 전원을 끈다.");
    }
     */

    // 기존에는 위처럼 했는데 유지보수를 향상시키기 위해서 표준화된 tv 인터페이스를 사용
    public void powerOn() {
        System.out.println("LgTV -- 전원을 켠다.");
    }

    public void powerOff() {
        System.out.println("LgTV -- 전원을 끈다.");
    }
}
