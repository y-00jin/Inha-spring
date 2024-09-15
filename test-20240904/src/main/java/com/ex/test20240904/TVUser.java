package com.ex.test20240904;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
    // main class add
    public static void main(String[] args) {
        // 1. 유지보수가 전혀 고려되지 않은 코드
        /*
        SamsungTV tv = new SamsungTV();
        tv.powerOn();
        tv.powerOff();
         */

        // 원시적인 유지보수임. 이렇게 유지보수를 하면 안 됨.
        /*
        LgTV tv = new LgTV();
        tv.turnOn();
        tv.turnOff();
        */

        // 2. 표준화된 인터페이스로 유지보수가 그나마 향상된 코드
        AbstractApplicationContext factory = new GenericXmlApplicationContext("app.xml");

        TV tv = (TV) factory.getBean("tv");
        // TV tv = new TV(); // 인터페이스이므로 이렇게는 객체 생성 x

        // TV tv = new SamsungTV(); // 삼성으로 바꾸고 싶으면 이렇게 하면 됨
        tv.powerOn();
        tv.powerOff();
    }
}
