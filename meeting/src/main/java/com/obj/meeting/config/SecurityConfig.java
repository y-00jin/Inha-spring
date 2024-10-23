package com.obj.meeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 설정파일
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 필터 추가
        http.csrf(auth -> auth.disable());

        // 인가 필터 추가 (모든 경로에 권한 처리 없이 설정)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll());

        http.formLogin(form -> form
                .loginPage("/login")        // 로그인 페이지 설정
                .defaultSuccessUrl("/menu", true)   // 성공 후 url
                .permitAll());  // 모든 권한 허용
        return http.build();
    }
}
