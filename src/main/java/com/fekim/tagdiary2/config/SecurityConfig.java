package com.fekim.tagdiary2.config;

import com.fekim.tagdiary2.security.handler.CustomLoginFailureHandler;
import com.fekim.tagdiary2.security.handler.CustomLoginSuccessHandler;
import com.fekim.tagdiary2.security.handler.CustomLogoutSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)   // preAuthorize를 사용하기 위함임
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/diarys/**/*").hasRole("USER");

                http.authorizeRequests()
                .antMatchers("/diarys/**/*").permitAll();

        http.formLogin().successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler());   // 인가/인증에 문제시 로그인 화면, .loginPage()나 .loginProcessUrl() 을 이용해서 커스텀 로그인 페이지 적용 가능

        http.csrf().disable();
        http.oauth2Login().successHandler(loginSuccessHandler());
        http.logout().logoutSuccessHandler(logoutSuccessHandler());
        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(userDetailsService);
    }

    @Bean
    public CustomLoginSuccessHandler loginSuccessHandler(){

        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public CustomLoginFailureHandler loginFailureHandler(){
        return new CustomLoginFailureHandler();
    }

}
