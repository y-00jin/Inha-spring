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

/**
 * OAuth2 인증 과정에서 사용자 정보를 가져오고, 사용자 정보를 데이터베이스에 저장하거나 업데이트
 */
@Service
public class MeetingOAuth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;

    public MeetingOAuth2UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. OAuth2 인증 제공자(예: 네이버, 카카오, 구글)로부터 사용자 정보 로드
        OAuth2User oAuth2User = super.loadUser(userRequest);    // 기본 사용자 정보 로드
        System.out.println("========= oAuth2User : " + oAuth2User.getAttributes());

        // 2. 식별자 확인 (소셜로그인 타입)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String username = null;
        String email = null;
        String name = null;

        // 3. 소셜 로그인 제공자별 사용자 정보 파싱
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

        // 4. DB 관리
        UserEntity existUserEntity = userRepository.findByUsername(registrationId + "_" +username); // 기존 회원인지 확인
        if(existUserEntity == null){    // 새 사용자
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registrationId + "_" +username);
            userEntity.setEmail(email);
            userEntity.setRole("ROLE_USER");
            userRepository.save(userEntity);

            return new MeetingOAuth2User(userEntity);
        }else{      // 기존 사용자 => 이메일과 권한 정보를 업데이트 후 저장
            existUserEntity.setEmail(email);
            existUserEntity.setRole("ROLE_USER");
            userRepository.save(existUserEntity);
            return new MeetingOAuth2User(existUserEntity);
        }

    }
}
