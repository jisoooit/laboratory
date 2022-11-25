package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.MemberHistory;
import com.example.bookmanager.repository.MemberHistoryRepository;
import com.example.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class MemberEntityListener {

    @PostPersist // post로 한 이유 - pre로 하면 member id값이 생성되지 않은채로 null로 들어가기 때문에
    @PostUpdate
    public void preUpd(Object o){
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        Member member = (Member) o;

        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());
        memberHistory.setMember(member);

        memberHistoryRepository.save(memberHistory);
    }
}
