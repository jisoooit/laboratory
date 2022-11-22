package com.example.practice01.javatest.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Developer {
    FRONT_END(1, "프론트"),
    BACK_END(2,"백엔드"),
    FULL_STACK(3, "풀스택"),
    NON_DEV(4,"non");

    private final int value;
    private final String korean;

    private static final Developer[] developers = Developer.values();

}
