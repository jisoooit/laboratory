package com.example.bookmanager.domain;

import java.time.LocalDateTime;

class MemberTest {
    @org.junit.jupiter.api.Test
    void test() {
        Member member = new Member();
        member.setName("grace");
        member.setEmail("grace@naver.com");
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());

        //Member member1 =new Member(1,"grace","grace@naver.com",LocalDateTime.now(),LocalDateTime.now());
        Member member2 =new Member("grace","grace@naver.com");

        Member member3 = Member.builder()
                .name("grace")
                .email("grace@naver.com")
                .build();
        System.out.println(member);
    }
}