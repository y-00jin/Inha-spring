package org.obj.test20240911;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
    public static void main(String[] args) {
        // 컨테이너 생성
        AbstractApplicationContext container = new GenericXmlApplicationContext("app.xml");

        // tv 객체 받아옴
        TV tv = (TV) container.getBean("tv");   // xml에서 설정한 id
        tv.powerOn();
        tv.powerOff();
    }
}
