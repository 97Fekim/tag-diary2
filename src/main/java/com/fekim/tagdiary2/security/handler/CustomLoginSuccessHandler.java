package com.fekim.tagdiary2.security.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Getter
@Setter
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("===============================");
        log.info("onAuthenticationSuccess");

        /* 로그인후, 일단은 모두 메인 홈페이지로 redirect 시킵니다 */
        redirectStratgy.sendRedirect(request, response, "/");

    }

}
