package com.ex.test20240904;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
    public static void main(String[] args) {

        AbstractApplicationContext factory = new GenericXmlApplicationContext("app.xml");

        TV tv = (TV) factory.getBean("tv");
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
    }
}

