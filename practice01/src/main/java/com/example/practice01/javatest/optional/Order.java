package com.example.practice01.javatest.optional;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    private Long id;
    private Date data;
    private Member member;
}
