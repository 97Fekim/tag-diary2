package com.fekim.tagdiary2.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Setter
@Getter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {

    private Long id;
    private String name;
    private String email;
    private boolean fromSocial;

    private Map<String, Object> attr;

    /* 일반 로그인 */
    public AuthMemberDTO(String username,
                         String password,
                         boolean fromSocial,
                         Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);

        this.email = username;
        this.fromSocial = fromSocial;
        // id와 name은 Service에서 set
    }

    /* OAuth 로그인 */
    public AuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attr) {

        this(username, password, fromSocial, authorities);
        this.attr = attr;
        // id와 name은 Service에서 set

    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
