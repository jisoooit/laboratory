package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

    Member findByEmail(String email);

    Member getByEmail(String email);

    Member readByEmail(String email);

    Member queryByEmail(String email);

    Member searchByEmail(String email);

    Member streamByEmail(String email);

    Member findMemberByEmail(String email);

    Member findSomethingByEmail(String email);

    Member findFirstByName(String name);

    Member findTopByName(String name);

    List<Member> findFirst2ByName(String name);

    List<Member> findTop2ByName(String name);

    List<Member> findLastByName(String name);

    List<Member> findByEmailAndName(String email, String name);

    List<Member> findByEmailOrName(String email, String name);

    List<Member> findByCreatedAtAfter(LocalDateTime yesterday);

    List<Member> findByIdAfter(Long Id);

    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<Member> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<Member> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<Member> findByIdBetween(Long id1, Long id2);

    List<Member> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    List<Member> findByIdIsNotNull();

    List<Member> findByNameIn(List<String> names);

    List<Member> findByNameStartingWith(String name);

    List<Member> findByNameEndingWith(String name);

    List<Member> findByNameContains(String name);

    List<Member> findByNameLike(String name);

    List<Member> findUserByNameIs(String name);
    List<Member> findUserByNameEquals(String name);


    List<Member> findTop1ByName(String name);

    List<Member> findTop1ByNameOrderByIdDesc(String name);

    List<Member> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<Member> findFirstByName(String name, Sort sort);

    Page<Member> findByName(String name, Pageable pageable);
}
