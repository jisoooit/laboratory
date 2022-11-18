package com.example.developer.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DevDto {
    String name;
    Integer age;
    LocalDateTime startAt;

    public void printLog(){
        log.info(getName());
    }
}

