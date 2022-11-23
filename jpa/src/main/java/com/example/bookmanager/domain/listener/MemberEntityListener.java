package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.MemberHistory;
import com.example.bookmanager.repository.MemberHistoryRepository;
import com.example.bookmanager.support.BeanUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class MemberEntityListener {

    @PrePersist
    @PreUpdate
    public void preUpd(Object o){
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        Member member = (Member) o;

        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setMemberId(member.getId());
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());

        memberHistoryRepository.save(memberHistory);
    }
}
