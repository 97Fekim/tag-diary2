package com.fekim.tagdiary2.security.service;

import com.fekim.tagdiary2.diary.domain.Member;
import com.fekim.tagdiary2.diary.domain.MemberRepository;
import com.fekim.tagdiary2.diary.domain.MemberRole;
import com.fekim.tagdiary2.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        log.info("=========================");
        log.info("userRequest : " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName : " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("=========================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k + " : " + v);
        });

        String email = null;

        if(clientName.equals("Google")) {    // 구글
            email = oAuth2User.getAttribute("email");
        } else if(clientName.equals("Naver")){  // 네이버 이용시, 이메일을 꺼내려면 response라는 Map에서 한번더 꺼내야 한다
            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("response");
            email = (String) response.get("email");
        }

        log.info("EMAIL : " + email);

        Member member = saveSocialMember(email);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        authMemberDTO.setId(member.getId());
        authMemberDTO.setName(member.getName());

        return authMemberDTO;
    }

    private Member saveSocialMember(String email){

        Optional<Member> result = memberRepository.findByEmail(email, true);

        if(result.isPresent()){
            return result.get();
        }

        Member member = Member.builder()
                .email(email)
                .name(email)
                .password(passwordEncoder.encode("1234"))
                .fromSocial(true)
                .build();

        member.addMemberRole(MemberRole.USER);

        /* 회원가입 */
        memberRepository.save(member);

        /* id를 얻기 위해, 다시 조회해서 가져옴 */
        Optional<Member> saved = memberRepository.findByEmail(email, true);

        return saved.get();

    }

}
