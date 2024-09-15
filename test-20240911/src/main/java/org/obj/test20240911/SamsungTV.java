package org.obj.test20240911;


public class SamsungTV implements TV {

    @Override
    public void powerOn() {
        System.out.println("삼성TV : 전원을 켠다.");
    }

    @Override
    public void powerOff() {
        System.out.println("삼성TV : 전원을 끈다.");
    }
}
