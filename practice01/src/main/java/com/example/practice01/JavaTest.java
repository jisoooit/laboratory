package com.example.practice01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaTest {
    private static long counter;

    private static void wasCalled() {
        counter++;
    }
    public static void main(String[] args) {


        List<String> list = Arrays.asList("abc1", "abc2","abc3");
        counter = 0;
        Stream<String> stream = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });
    }
}
