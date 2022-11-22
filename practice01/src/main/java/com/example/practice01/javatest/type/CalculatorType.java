package com.example.practice01.javatest.type;

import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public enum CalculatorType {
    CALC_A(value -> value),
    CALC_B(value -> value *10),
    CALC_C(value -> value * 3),
    CALC_ETC(value -> 0L);

    private Function<Long, Long> expressions;

    public long calculate(long value) {
        return expressions.apply(value);
    }
}
