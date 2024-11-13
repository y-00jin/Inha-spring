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
@EnableWebSecurity(debug = false)
public class SecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;

    private JwtAuth jwtAuth;

    private MeetingOAuth2UserService meetingOAuth2UserService;

    private MeetingOAuth2SuccessHandler meetingOAuth2SuccessHandler;

    public SecurityConfig( AuthenticationConfiguration authenticationConfiguration, JwtAuth jwtAuth, MeetingOAuth2UserService meetingOAuth2UserService, MeetingOAuth2SuccessHandler meetingOAuth2SuccessHandler){
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtAuth = jwtAuth;
        this.meetingOAuth2UserService = meetingOAuth2UserService;
        this.meetingOAuth2SuccessHandler = meetingOAuth2SuccessHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        //여기에서 LoginAuthFilter.java의 successfulAuthentication 함수 기능을 대신 하는 것.
        return (request, response, authentication)->{
            String username = authentication.getName();
            String role = authentication.getAuthorities().iterator().next().getAuthority();

            String token = jwtAuth.generateToken(username, role);
            response.addHeader("Authorization", "Bearer "+token);
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 디폴트 필터의 기본값이 enable되어 있는 설정을 disable로 바꿔서 뚫어줌 -> 브라우저에서 요청하는 걸 막음
        http.csrf(auth-> auth.disable());

        // 인가 필터 추가 (모든 경로에 권한 처리 없이 설정)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/getUserList").hasRole("ADMIN")      // hasRole 은 ROLE_ADMIN 형태로 권한 설정한 경우 or hasAuthority는 ADMIN 형태일때 사용
                .requestMatchers("/getGroupList", "/saveGroup").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll());

        // 인증 (람다형식)
        http.formLogin(form -> form
                .loginPage("/login")        // 로그인 페이지 설정
                .successHandler(authenticationSuccessHandler())
//                .defaultSuccessUrl("/menu", true)   // 성공 후 url => 이거 대신 Handler를 사용함
                .permitAll()  // 모든 권한 허용
        );

        // oauth 로그인 필터 추가
        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .userInfoEndpoint(config -> config.userService(meetingOAuth2UserService))
                .successHandler(meetingOAuth2SuccessHandler)
        );


        // iframe 부분 접근할 수 있게
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

        // 세션 유지 막기
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // LoginAuthFilter 대신 UsernamePasswordAuthenticationFilter로 이동하는 것. 필터를 줄이기 위해서.
        http.addFilterBefore(new JwtAuthFilter(jwtAuth), UsernamePasswordAuthenticationFilter.class);

//        http.addFilterBefore(new JwtAuthFilter(jwtAuth), LoginAuthFilter.class);   // LoginAuthFilter 앞에 JwtAuthFilter 추가
//
//        // 필터 추가 - UsernamePasswordAuthenticationFilter 대신에 사용할거라 그 위치에 넣을 거임 => addFilterAt
//        http.addFilterAt(new LoginAuthFilter(authenticationManager(authenticationConfiguration), jwtAuth), UsernamePasswordAuthenticationFilter.class);




        return http.build();  // 최종리턴
    }
}
