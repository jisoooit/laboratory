package com.example.practice01.javatest;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaTest {
    public static void main(String[] args) {
        MultiValueMap<String, Integer> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, List<Integer>> map2 = new HashMap<>();

        multiValueMap.add("a",1);
        multiValueMap.add("a",2);
        multiValueMap.add("a",3);

        map.put("a",1);
        map.put("a",2);
        map.put("a",3);

        System.out.println(multiValueMap.get("a"));
        System.out.println(map.get("a"));


        List<Integer> list = Arrays.asList(1, 2, null);
        list.set(1, 10); // OK

        List<Integer> list2 = List.of(1, 2, 3);

        list.stream().forEach(System.out::println);
        list2.stream().forEach(System.out::println);


    }
}
