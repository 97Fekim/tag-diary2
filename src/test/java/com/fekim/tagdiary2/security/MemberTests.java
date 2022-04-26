package com.fekim.tagdiary2.security;

import com.fekim.tagdiary2.member.domain.Member;
import com.fekim.tagdiary2.member.domain.MemberRepository;
import com.fekim.tagdiary2.member.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootTest
//public class MemberTests {
//
//    @Test
//    public void helloWorld(){
//
//    }
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void addRole(){
//
//        Member member1 = Member.builder()
//                .name("Romeo")
//                .password(passwordEncoder.encode("1234"))
//                .email("Romeo@gmail.com")
//                .fromSocial(false)
//                .build();
//
//        Member member2 = Member.builder()
//                .name("Juliet")
//                .password(passwordEncoder.encode("1234"))
//                .email("Juliet@gmail.com")
//                .fromSocial(false)
//                .build();
//
//        member1.addMemberRole(MemberRole.USER);
//        member2.addMemberRole(MemberRole.USER);
//
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//
//    }
//
//    @Test
//    public void testJoin(){
//        Member member = Member.builder()
//                .name("join_test_member")
//                .password(passwordEncoder.encode("1234"))
//                .build();
//
//        member.addMemberRole(MemberRole.USER);
//
//        memberRepository.save(member);
//    }
//
//}
