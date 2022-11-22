package com.example.practice01.javatest;

import java.util.HashMap;
import java.util.Map;

public class mapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("B",2);
        map.put("A",1);

        System.out.println(map);
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        map.entrySet().stream()
                .map(Map.Entry<String, Integer>::getKey)
                .forEach(System.out::println);
    }
}
