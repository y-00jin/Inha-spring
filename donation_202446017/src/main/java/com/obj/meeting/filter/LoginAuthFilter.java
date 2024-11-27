package com.obj.meeting.filter;

import com.obj.meeting.config.JwtAuth;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// 로그인 확인은 authenticationManager가 하고, 성공과 실패는 여기서 확인함.
//@AllArgsConstructor
public class LoginAuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JwtAuth jwtAuth;

    public LoginAuthFilter(AuthenticationManager authenticationManager, JwtAuth jwtAuth){
        this.authenticationManager = authenticationManager;
        this.jwtAuth = jwtAuth;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // request에서 id, pw를 가져와서 매니저에게 전달하여 Authentication를 리턴
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // 토큰화 시켜서 전달 해줘야 함.
        // 토큰 만들기 (authorities는 없어서 null 넣어줌)
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공 시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("==== LoginAuthFilter 로그인 성공 ====");

        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        String token = jwtAuth.generateToken(username, role);
        System.out.println(">>> LoginAuthFilter 토큰 : " + token);
        response.addHeader("Authorization", "Bearer " + token);
    }

    // 로그인 실패 시
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("==== 로그인 실패 ====");
    }
}
