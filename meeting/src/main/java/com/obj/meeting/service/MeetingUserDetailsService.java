package com.obj.meeting.service;

import com.obj.meeting.dto.MeetingUserDetails;
import com.obj.meeting.entity.UserEntity;
import com.obj.meeting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MeetingUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            System.out.println("========== 로그인 성공 ==========");
            return new MeetingUserDetails(userEntity);  // 회원 정보가 존재하면 MeetingUserDetails에 조회한 userEntity 담아서 리턴
        }
        return null;
        // return 하면 AuthenticationManager가 받아감 -> 인증 처리 완료 후 세션 작업 필요
    }
}
