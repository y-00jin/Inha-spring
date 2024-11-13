package com.obj.meeting.oauth2;

import com.obj.meeting.config.JwtAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MeetingOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private JwtAuth jwtAuth;

    public MeetingOAuth2SuccessHandler(JwtAuth jwtAuth){
        this.jwtAuth = jwtAuth;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        String token = jwtAuth.generateToken(username, role);

        System.out.println("#####" + token);

        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(false);  // js에서 쿠키 접근 가능하게 설정
        cookie.setSecure(true);     // https 허용
        cookie.setPath("/");        // 브라우저 모든 서비스에서 접근 가능

        response.addCookie(cookie);
        response.sendRedirect("/menu");
    }
}
