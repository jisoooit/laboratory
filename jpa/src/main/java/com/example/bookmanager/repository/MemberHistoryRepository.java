package com.example.bookmanager.repository;

import com.example.bookmanager.domain.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
    List<MemberHistory> findByMemberId(Long memberId);
}
