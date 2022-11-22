package com.example.practice01.javatest;

import com.example.practice01.javatest.type.CalculatorType;
import com.example.practice01.javatest.type.Developer;
import com.example.practice01.javatest.type.PayGroup;
import com.example.practice01.javatest.type.PayType;

public class EnumTest {
    public static void main(String[] args) {
        for(Developer d : Developer.values()){
            System.out.println(d);
            System.out.println(d.name() + d.ordinal());
            System.out.println(d.getValue()+ d.getKorean());
        }
        System.out.println(Developer.BACK_END);
        System.out.println();

        //calculator
        CalculatorType code = CalculatorType.CALC_C;
        long result = code.calculate(100);
        System.out.println(result);
        System.out.println();

        //payGroup

        PayType payCode = PayType.TOSS;
        PayGroup payGroup = PayGroup.findByPayCode(payCode);

        System.out.println(payGroup.name());
        System.out.println(payGroup.getTitle());
    }
}
