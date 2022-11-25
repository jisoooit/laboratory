package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Gender;
import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.MemberHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

    @Test
    void crud() {
//        memberRepository.save(new Member("grace","grace@baver.com");
//        System.out.println(memberRepository.findAll());
        List<Member> members = memberRepository.findAll();

        List<Member> members1 = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<Member> members2 = memberRepository.findAllById(Lists.newArrayList(1L,3L,5L));

        Member member1 =new Member("jack","jack@naver.com");
        Member member2 =new Member("jackson","jackson@naver.com");

        memberRepository.saveAll(Lists.newArrayList(member1, member2));

        members.forEach(System.out::println);
        members1.forEach(System.out::println);
        members2.forEach(System.out::println);


        Member member = memberRepository.findById(1L).orElse(null);
        System.out.println(member);
    }

    @Test
    void select() {
        System.out.println(memberRepository.findByName("grace1"));

        System.out.println("findByEmail: " + memberRepository.findByEmail("grace1@naver.com"));
        System.out.println("getByEmail: " + memberRepository.getByEmail("grace1@naver.com"));
        System.out.println("readByEmail: " + memberRepository.readByEmail("grace1@naver.com"));
        System.out.println("queryByEmail: " + memberRepository.queryByEmail("grace1@naver.com"));
        System.out.println("searchByEmail: " + memberRepository.searchByEmail("grace1@naver.com"));
        System.out.println("streamByEmail: " + memberRepository.streamByEmail("grace1@naver.com"));
        System.out.println("findMemberByEmail: " + memberRepository.findMemberByEmail("grace1@naver.com"));
        System.out.println("findSomethingByEmail: " + memberRepository.findSomethingByEmail("grace1@naver.com")); //something은 무시가 되는 것 같다.

        System.out.println("findTopByName: " + memberRepository.findTopByName("grace1"));
        System.out.println("findFirstByName: " + memberRepository.findFirstByName("grace1"));

        System.out.println("findTop2ByName: " + memberRepository.findTop2ByName("grace1"));
        System.out.println("findFirst2ByName: " + memberRepository.findFirst2ByName("grace1"));

        System.out.println("findLastByName: " + memberRepository.findLastByName("grace1")); //last가 무시되고 findByName과 같은 쿼리로 동작한다.


    }

    @Test
    void selectDetail() {
        System.out.println("findByEmailAndName: " + memberRepository.findByEmailAndName( "grace1@naver.com","grace1"));
        System.out.println("findByEmailOrName: " + memberRepository.findByEmailOrName("grace1@naver.com","grace1"));

        System.out.println("findByCreatedAtAfter: " + memberRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter: " + memberRepository.findByIdAfter(4L));

        System.out.println("findByCreatedAtGreaterThan: " + memberRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual: " + memberRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByCreatedAtBetween: " + memberRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween: " + memberRepository.findByIdBetween(1L, 4L));

        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual: " + memberRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 4L));

        System.out.println("findByIdIsNotNull: " + memberRepository.findByIdIsNotNull());

        System.out.println("findByNameIn: " + memberRepository.findByNameIn(Lists.newArrayList("grace1", "grace2")));

        System.out.println("findByNameStartingWith: " + memberRepository.findByNameStartingWith("gra"));
        System.out.println("findByNameEndingWith: " + memberRepository.findByNameEndingWith("ce1"));
        System.out.println("findByNameContains: " + memberRepository.findByNameContains("rac"));

        System.out.println("findByNameLike: " + memberRepository.findByNameLike("%rac%"));


    }

    @Test
    void pagingAndSorting() {
        System.out.println("findTop1ByName: " + memberRepository.findTop1ByName("grace1"));
        System.out.println("findLast1ByName: " + memberRepository.findLastByName("grace1"));
        System.out.println("findTop1ByNameOrderByIdDesc: " + memberRepository.findTop1ByNameOrderByIdDesc("grace1")); //순서 역으로 뒤집은 다음 하나 가져옴
        System.out.println("findFirstByNameOrderByIdDescEmailAsc: " + memberRepository.findFirstByNameOrderByIdDescEmailAsc("grace1")); // id 역순, 이메일 정순

        System.out.println("findFirstByNameWithSortParams: " + memberRepository.findFirstByName("grace1", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findFirstByNameWithSortParams: " + memberRepository.findFirstByName("grace1", getSort()));

        System.out.println("findByNameWithPaging: " + memberRepository.findByName("grace1", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("findByNameWithPaging: " + memberRepository.findByName("grace1", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());


    }

    @Test
    void insertAndUpdateTest() {
        Member member = new Member();
        member.setName("grace");
        member.setEmail("grace@naver.com");

        memberRepository.save(member);

        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member2.setName("graceee");

        memberRepository.save(member2);
    }

    @Test
    void enumTest() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member.setGender(Gender.MALE);

        memberRepository.save(member);

        memberRepository.findAll().forEach(System.out::println);

        System.out.println(memberRepository.findRawRecord().get("gender"));
        List<String> list = Lists.newArrayList("one","another", null, "one_more");
    }
    private Sort getSort(){
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt")
        );
    }

    @Test
    void prePersistTest() {
        Member member = new Member();
        member.setEmail("elsa@naver.com");
        member.setName("elsa");

        memberRepository.save(member);

        System.out.println(memberRepository.findByName("elsa"));
    }

    @Test
    void preUpdateTest() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is: "+ member);

        member.setName("elsssa");
        memberRepository.save(member);

        System.out.println("to-be : "+ memberRepository.findAll().get(0));
    }

    @Test
    void memberHistoryTest() {
        Member member = new Member();
        member.setName("elsa");
        member.setEmail("elsa@gmail.com");

        memberRepository.save(member);

        member.setName("elsaupdate");

        memberRepository.save(member);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void memberRelationTest() {
        Member member = new Member();
        member.setName("elsa");
        member.setEmail("elsa@naver.com");
        member.setGender(Gender.FEMALE);

        memberRepository.save(member);

        member.setName("anna");
        memberRepository.save(member);

        member.setEmail("anna@gmail.com");
        memberRepository.save(member);

        //memberHistoryRepository.findAll().forEach(System.out::println);

        List<MemberHistory> result = memberHistoryRepository.findByMemberId(
                memberRepository.findByEmail("anna@gmail.com").getId()
        );

        result.forEach(System.out::println);

        List<MemberHistory> result2 = memberRepository.findByEmail("anna@gmail.com").getMemberHistories();

        result.forEach(System.out::println);
    }
}