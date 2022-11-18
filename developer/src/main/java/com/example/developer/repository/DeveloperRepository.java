package com.example.developer.repository;

import com.example.developer.entity.Developer;
import com.example.developer.code.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByMemberId(String memberId);


    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}
