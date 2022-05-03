package com.fekim.tagdiary2.security;

import com.fekim.tagdiary2.diary.domain.Member;
import com.fekim.tagdiary2.diary.domain.MemberRepository;
import com.fekim.tagdiary2.diary.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class MemberTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testJoin(){

        Member member1 = Member.builder()
                .name("Messi")
                .password(passwordEncoder.encode("1234"))
                .email("Messi@gmail.com")
                .fromSocial(false)
                .build();

        Member member2 = Member.builder()
                .name("Ronaldo")
                .password(passwordEncoder.encode("1234"))
                .email("Ronaldo@gmail.com")
                .fromSocial(false)
                .build();

        member1.addMemberRole(MemberRole.USER);
        member2.addMemberRole(MemberRole.USER);

        memberRepository.save(member1);
        memberRepository.save(member2);

    }

}
