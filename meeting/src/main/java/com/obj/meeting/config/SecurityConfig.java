package com.obj.meeting.config;

import com.obj.meeting.filter.JwtAuthFilter;
import com.obj.meeting.filter.LoginAuthFilter;
import com.obj.meeting.oauth2.MeetingOAuth2SuccessHandler;
import com.obj.meeting.service.MeetingOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)   // Spring Security 활성화
public class SecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;

    private JwtAuth jwtAuth;

    private MeetingOAuth2UserService meetingOAuth2UserService;

    private MeetingOAuth2SuccessHandler meetingOAuth2SuccessHandler;

    /**
     * 정의된 객체를 DI 방식으로 주입
     * @param authenticationConfiguration
     * @param jwtAuth
     * @param meetingOAuth2UserService
     * @param meetingOAuth2SuccessHandler
     */
    public SecurityConfig( AuthenticationConfiguration authenticationConfiguration, JwtAuth jwtAuth, MeetingOAuth2UserService meetingOAuth2UserService, MeetingOAuth2SuccessHandler meetingOAuth2SuccessHandler){
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtAuth = jwtAuth;
        this.meetingOAuth2UserService = meetingOAuth2UserService;
        this.meetingOAuth2SuccessHandler = meetingOAuth2SuccessHandler;
    }

    /**
     * 인증 요청 컴포넌트
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();  // Spring Security가 제공하는 기본 AuthenticationManager 가져옴
    }

    /**
     * 사용자 비밀번호를 해시 처리하기 위한 BCrypt 암호화 객체 Bean으로 등록
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 시큐리티 인증 성공 커스텀 핸들러
     * 인증 성공 후 JWT 토큰 생성 -> 클라이언트가 인증 상태를 유지하는데 사용
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        //여기에서 LoginAuthFilter.java의 successfulAuthentication 함수 기능을 대신 하는 것.
        return (request, response, authentication)->{
            String username = authentication.getName();
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            System.out.println(">>> AuthenticationSuccessHandler role : " + role);
            String token = jwtAuth.generateToken(username, role);   // JWT 토큰 생성
            System.out.println(">>> AuthenticationSuccessHandler 토큰 : " + token);
            response.addHeader("Authorization", "Bearer "+token);   // Bearer 방식으로 HTTP 응답 헤더에 추가
        };
    }


    /**
     * Spring Security 필터 체인 설정
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 보호 기능을 비활성화 (REST API를 사용하는 경우 주로 비활성화)
        http.csrf(auth-> auth.disable());

        // 경로별 권한 설정 (인가 필터 추가)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/getUserList").hasRole("ADMIN")      // hasRole 은 ROLE_ADMIN 형태로 권한 설정한 경우 or hasAuthority는 ADMIN 형태일때 사용
                .requestMatchers("/getGroupList", "/saveGroup").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()); // 나머지 요청은 인증 없이 접근 허용

        // 기본 로그인 방식을 폼 로그인으로 설정
        http.formLogin(form -> form
                .loginPage("/login")        // 로그인 페이지 설정
                .successHandler(authenticationSuccessHandler()) // 로그인 성공 시 authenticationSuccessHandler => JWT 토큰 생성 후 헤더에 추가하는 작업 호출
//                .defaultSuccessUrl("/menu", true)   // 성공 후 url => 이거 대신 Handler를 사용함
                .permitAll()  // 모든 권한 허용
        );

        // oauth 로그인 필터 추가
        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .userInfoEndpoint(config -> config.userService(meetingOAuth2UserService))   // 사용자 정보를 meetingOAuth2UserService를 통해 가져오도록 설정
                .successHandler(meetingOAuth2SuccessHandler)    // 성공 시 MeetingOAuth2SuccessHandler 호출
        );


        // 보안 기본 정책으로 차단된 IFrame 접근을 허용
        http.headers(headers -> headers
                .frameOptions(frame->frame
                        .disable()));

        // 접근 오류 페이지 설정
        http.exceptionHandling(exception-> exception.accessDeniedPage("/forbidden"));

        // 로그아웃 필터 수정
        http.logout(logout->logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index.html")
                .deleteCookies("JSESSIONID")    // 쿠키 삭제
                .invalidateHttpSession(true)    // http 세션 삭제
        );



        // 사용자 정의 JwtAuthFilter를 Security 필터 체인에 추가 (LoginAuthFilter 대신 시용)
        // UsernamePasswordAuthenticationFilter 앞에 실행되도록 설정
        http.addFilterBefore(new JwtAuthFilter(jwtAuth), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // 세션을 사용하지 않는 무상태(stateless) 방식으로 설정

//        http.addFilterBefore(new JwtAuthFilter(jwtAuth), LoginAuthFilter.class);   // LoginAuthFilter 앞에 JwtAuthFilter 추가
//
//        // 필터 추가 - UsernamePasswordAuthenticationFilter 대신에 사용할거라 그 위치에 넣을 거임 => addFilterAt
//        http.addFilterAt(new LoginAuthFilter(authenticationManager(authenticationConfiguration), jwtAuth), UsernamePasswordAuthenticationFilter.class);

        return http.build();  // 최종리턴
    }
}
