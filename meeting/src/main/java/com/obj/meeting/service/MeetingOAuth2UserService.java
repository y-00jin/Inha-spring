package com.obj.meeting.service;

import com.obj.meeting.dto.MeetingOAuth2User;
import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MeetingOAuth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;

    public MeetingOAuth2UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("========= oAuth2User : " + oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // 식별자 확인 (소셜로그인 타입)

        String username = null;
        String email = null;
        String name = null;

        if(registrationId.equals("naver")){
            Map<String, Object> attribute = oAuth2User.getAttribute("response");
            username = attribute.get("id").toString();
            email = attribute.get("email").toString();
            name = attribute.get("name").toString();
        } else if (registrationId.equals("kakao")) {
            return null;
        } else if (registrationId.equals("google")) {
            return null;
        }else{
            return null;
        }
        System.out.println("################");
        System.out.println(username);
        System.out.println(email);
        System.out.println(name);
        System.out.println("################");

        // DB저장
        UserEntity existUserEntity = userRepository.findByUsername(registrationId + "_" +username);
        if(existUserEntity == null){
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registrationId + "_" +username);
            userEntity.setEmail(email);
            userEntity.setRole("ROLE_USER");
            userRepository.save(userEntity);

            return new MeetingOAuth2User(userEntity);
        }else{
            existUserEntity.setEmail(email);
            existUserEntity.setRole("ROLE_USER");
            userRepository.save(existUserEntity);
            return new MeetingOAuth2User(existUserEntity);
        }

    }
}
