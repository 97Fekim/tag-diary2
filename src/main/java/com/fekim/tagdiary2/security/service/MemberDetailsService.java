package com.fekim.tagdiary2.security.service;

import com.fekim.tagdiary2.diary.domain.Member;
import com.fekim.tagdiary2.diary.domain.MemberRepository;
import com.fekim.tagdiary2.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsService loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.findByNameAndSocial(username, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Id or social");
        }

        Member member = result.get();

        log.info("==============================");
        log.info(member);

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getName(),   // 임시로 이메일을 name으로 처리
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setName(member.getName());
        authMemberDTO.setId(member.getId());

        return authMemberDTO;
    }
}
