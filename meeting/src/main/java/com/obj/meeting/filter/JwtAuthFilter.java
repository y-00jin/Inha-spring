package com.obj.meeting.filter;

import com.obj.meeting.config.JwtAuth;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT 토큰을 추출하고 검증하여 사용자의 인증 정보를 Spring Security의 SecurityContext에 설정하는 역할
 * OncePerRequestFilter : 요청 당 한번만 실행됨
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtAuth jwtAuth;

    public JwtAuthFilter(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization"); // Header에 담긴 토큰
        String token = null;

        // 1. 토큰 검증 작업
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7); // "Bearer " 이후의 순수 토큰 값
        } else {
            // 토큰 없는 경우
            filterChain.doFilter(request, response);    // 다음 필터로 이동
            return;
        }

        // 2. JWT 토큰에서 사용자 정보 추출
        String username = jwtAuth.getUsername(token);   // 사용자명 조회
        String role = jwtAuth.getRole(token);           // 권한 조회

        // 3. Spring Security 인증 설정
        UserDetails userDetails = new User(username, "1234", Collections.singletonList(new SimpleGrantedAuthority(role)));  // Spring Security의 인증 객체인 UserDetails 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());   // 인증 객체로 사용
        SecurityContextHolder.getContext().setAuthentication(authentication);   // Spring Security의 SecurityContext에 설정

        filterChain.doFilter(request, response);    // 현재 필터의 작업이 끝나면 요청을 다음 필터로 전달

    }
}
