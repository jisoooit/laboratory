package com.example.practice01.javatest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsTest {

    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a","bb","ccc","dd");

        List<String> collectionsToList = givenList.stream()
                .collect(Collectors.toList());

        Set<String> collectionsToSet = givenList.stream()
                .collect(Collectors.toSet());

        List<String> collectionsToCollection = givenList.stream()
                .collect(Collectors.toCollection(LinkedList::new));

        Set<String> collectionsToCollection2 = givenList.stream()
                .collect(Collectors.toCollection(TreeSet::new));

        Map<String, Integer> collectionsToMap = givenList.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (item , identicalItem) -> item));

        List<String> collectngAndThen = givenList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList ));

        String joining = givenList.stream()
                .collect(Collectors.joining());

        String joining2 = givenList.stream()
                .collect(Collectors.joining(" "));

        String joining3 = givenList.stream()
                .collect(Collectors.joining(" ","prefix","post"));


        Long counting = givenList.stream().collect(Collectors.counting());

        Double averagingDouble = givenList.stream()
                .collect(Collectors.averagingDouble(String::length));

        Double summingDouble = givenList.stream()
                .collect(Collectors.summingDouble(String::length));

        Map<Integer, Set<String>> groupingBy = givenList.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));

        Map<Boolean, List<String>> partitioningBy = givenList.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 2 ));

        System.out.println(collectionsToList);
        System.out.println(collectionsToSet);
        System.out.println(collectionsToMap);
        System.out.println(collectionsToCollection);
        System.out.println(collectionsToCollection2);
        System.out.println(collectngAndThen);
        System.out.println(joining);
        System.out.println(joining2);
        System.out.println(joining3);
        System.out.println(averagingDouble);
        System.out.println(summingDouble);
        System.out.println(groupingBy);
        System.out.println(partitioningBy);

    }
}
