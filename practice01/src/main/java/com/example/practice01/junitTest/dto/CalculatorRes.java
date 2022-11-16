package com.example.practice01.junitTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorRes {

    private int result;
    private Body response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body{
        String resultCode="OK";
    }
}
