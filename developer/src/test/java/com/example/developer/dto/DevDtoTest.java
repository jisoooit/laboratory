package com.example.developer.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DevDtoTest {
    @Test
    void test() {
        DevDto devDto = new DevDto();

        devDto.setName("grace");
        devDto.setAge(20);
        devDto.setStartAt(LocalDateTime.now());
        System.out.println(devDto.getName());

        DevDto devDto1 = DevDto.builder()
                .name("snow")
                .age(21)
                .startAt(LocalDateTime.now())
                .build();

        devDto1.printLog();
    }
}