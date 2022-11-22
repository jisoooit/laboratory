package com.example.practice01.javatest;

import com.example.practice01.dto.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<List<String>> collectionList = Arrays.asList(Arrays.asList("a","b"), Arrays.asList("c","d"));
        String[][] arrayList = new String[][]{{"a2","b2"},{"c2,d2"}};


        List<String> flatList = collectionList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("flatList: " + flatList);

        List<String> flatList2 = Arrays.stream(arrayList)
                .flatMap(s -> Arrays.stream(s))
                .collect(Collectors.toList());

        System.out.println("flatList2: " +flatList2);

        List<String> flatList3 = Arrays.stream(arrayList)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("flatList3: " +flatList3);

        Stream<String> stream = Stream.of("hello world");
        stream.map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        Stream<String> stream2= Stream.of("hello world");
        stream2.flatMap(str -> Arrays.stream(str.split("")))
                .distinct()
                .forEach(System.out::println);


        List<User> users = Arrays.asList(
                new User(1,"jisu","naver.com"),
                new User(2,"ou","daum.com"),
                new User(3,"elsa","gmail.com")
        );

        users.stream().mapToInt(User::getUserId)
                .forEach(user -> System.out.println(user));

        users.stream().flatMapToInt( user -> IntStream.of(user.getUserId()))
                .average()
                .ifPresent( avg -> System.out.println(avg));
    }
}
