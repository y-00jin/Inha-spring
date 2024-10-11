package com.ex.test20240904;

public class BeanFactory {
    // BeanFactory가 컨테이너라고 보면 됨(컨테이너의 역할을 해주고 있음!)
    public Object getBean(String beanName) {
        if(beanName.equals("samsung")){     // bean네임에 따라 객체 생성이 달라짐
            return new SamsungTV();
        } else if (beanName.equals("lg")){
            return new LgTV();
        }
        return null;
    }
}


