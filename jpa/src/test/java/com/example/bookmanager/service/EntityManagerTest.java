package com.example.bookmanager.service;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select m from Member m").getResultList());
        // == MemberRepository.findAll();
    }

    @Test
    void cacheFindTest() {
        System.out.println(memberRepository.findByEmail("grace1@naver.com"));
        System.out.println(memberRepository.findByEmail("grace1@naver.com"));
        System.out.println(memberRepository.findByEmail("grace1@naver.com"));

        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findById(1L).get());
    }

    @Test
    void cacheFindTest2() {
        Member member = memberRepository.findById(1L).get();
        member.setName("elsa");

//        memberRepository.save(member);

//        memberRepository.flush();


        member.setEmail("elsa@naver.com");

//        memberRepository.save(member);

        memberRepository.flush();

        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findByEmail("elsa@naver.com"));

    }
}
