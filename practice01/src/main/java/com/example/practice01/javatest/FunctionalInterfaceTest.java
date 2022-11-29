package com.example.practice01.javatest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        Supplier<Integer> s = () -> (int)(Math.random()*100)+1;
        Consumer<Integer> c = i -> System.out.println(i+",");
        Predicate<Integer> p = i->i%2==0;
        Function<Integer, Integer> f = i -> i/10*10;

        List<Integer> list = new ArrayList<>();
        makeRandomList(s,list);
        System.out.println(list);

        printEvenNum(p,c,list);
        List<Integer> newList = doSomething(f,list);
        System.out.println(newList);

        Function<String, Integer>	f2  = (s2) -> Integer.parseInt(s2, 16);
        Function<Integer, String>	g  = (i) -> Integer.toBinaryString(i);

        Function<String, String>	h  = f2.andThen(g);
        Function<Integer, Integer>  h2 = f2.compose(g);

        System.out.println(h.apply("FF")); // "FF" → 255 → "11111111"
        System.out.println(h2.apply(2));   // 2 → "10" → 16


        Function<String, String> f3 = x -> x; // 항등 함수(identity function)
        System.out.println(f3.apply("AAA"));  // AAA가 그대로 출력됨

        Predicate<Integer> p2 = i -> i < 100;
        Predicate<Integer> q = i -> i < 200;
        Predicate<Integer> r = i -> i%2 == 0;
        Predicate<Integer> notP = p2.negate(); // i >= 100

        Predicate<Integer> all = notP.and(q).or(r);
        System.out.println(all.test(150));       // true

        String str1 = "abc";
        String str2 = "abc";

        // str1과 str2가 같은지 비교한 결과를 반환
        Predicate<String> p3 = Predicate.isEqual(str1);
        boolean result = p3.test(str2);
        System.out.println(result);
    }

    private static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<>(list.size());
        for(T i : list){
            newList.add(f.apply(i));
        }
        return newList;
    }

    private static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.println("[");
        for(T i : list){
            if (p.test(i)){
                c.accept(i);
            }
        }

    }

    private static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for (int i = 0; i < 10; i++) {
            list.add(s.get());
        }
    }
}
