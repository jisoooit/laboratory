package com.example.bookmanager.repository;

import com.example.bookmanager.domain.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}
