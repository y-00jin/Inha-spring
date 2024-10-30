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
        http.csrf(auth -> auth.disable());  // csrf 기본값은 inable

        // 인가 필터 추가 (모든 경로에 권한 처리 없이 설정)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/getUserList").hasRole("ADMIN")      // hasRole 은 ROLE_ADMIN 형태로 권한 설정한 경우 or hasAuthority는 ADMIN 형태일때 사용
                .requestMatchers("/getGroupList", "/saveGroup", "/saveUser").hasAnyRole("USER", "ADMIN")   // hasAnyRole은 여러 권한 설정
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()   // 권한 인증 후
        );

        http.formLogin(form -> form
                .loginPage("/login")        // 로그인 페이지 설정
                .defaultSuccessUrl("/menu", true)   // 성공 후 url
                .permitAll());  // 모든 권한 허용

        // iframe 접근 권한 설정
        http.headers(headers -> headers
                .frameOptions(frame -> frame
                        .disable()));


        http.exceptionHandling(exception -> exception.accessDeniedPage("/forbidden"));

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index.html")
                .deleteCookies("JSESSIONID")    // 쿠키 삭제
                .invalidateHttpSession(true)    // http 세션 삭제
        );
        return http.build();
    }
}
