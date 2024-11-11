package com.obj.meeting.config;

import com.obj.meeting.filter.JwtAuthFilter;
import com.obj.meeting.filter.LoginAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;

    private JwtAuth jwtAuth;

    public SecurityConfig( AuthenticationConfiguration authenticationConfiguration, JwtAuth jwtAuth){
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtAuth = jwtAuth;
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

    // 필터체인을 등록하지 않으면 기본 필터체인 15개가 나오고, 다음 코드처럼 필터체인 명시하면 그것만 됨
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
                .defaultSuccessUrl("/menu", true)   // 성공 후 url
                .permitAll()  // 모든 권한 허용
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

        http.addFilterBefore(new JwtAuthFilter(jwtAuth), LoginAuthFilter.class);   // LoginAuthFilter 앞에 JwtAuthFilter 추가

        // 필터 추가 - UsernamePasswordAuthenticationFilter 대신에 사용할거라 그 위치에 넣을 거임 => addFilterAt
        http.addFilterAt(new LoginAuthFilter(authenticationManager(authenticationConfiguration), jwtAuth), UsernamePasswordAuthenticationFilter.class);
        // 세션 유지 막기
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();  // 최종리턴
    }
}
