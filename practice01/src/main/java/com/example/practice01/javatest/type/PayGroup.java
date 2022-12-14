package com.example.practice01.javatest.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public enum PayGroup {

    CASH("현금", Arrays.asList(PayType.ACCOUNT_TRANSFER, PayType.REMITTANCE, PayType.ON_SITE_PAYMENT, PayType.TOSS)),
    CARD("카드",Arrays.asList(PayType.PAYCO,PayType.CARD,PayType.KAKAO_PAY, PayType.BAEMIN_PAY)),
    ETC("기타",Arrays.asList(PayType.POINT, PayType.COUPON)),
    EMPTY("없음", Collections.emptyList());

    private final String title;
    private final List<PayType> payList;

    public static PayGroup findByPayCode(PayType payType){
        return Arrays.stream(PayGroup.values())
                .filter(payGroup -> payGroup.hasPayCode(payType))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasPayCode(PayType payType){
        return payList.stream()
                .anyMatch(pay -> pay == payType);
    }
}
