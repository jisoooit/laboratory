package com.example.practice01.javatest.optional;

import java.time.LocalDateTime;
import java.util.*;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<Member> maybeMember = Optional.empty();

        Member defaultMember = new Member();
        Optional<Member> maybeMember2 = Optional.of(defaultMember);


        Member memberA = new Member();
        maybeMember = Optional.of(memberA);

        maybeMember = Optional.ofNullable(memberA);
        Optional<Member> maybeNotMember = Optional.ofNullable(null);

        System.out.println(maybeMember.get());
        System.out.println(maybeNotMember.orElse(defaultMember));
        System.out.println(maybeNotMember.orElseGet(() -> defaultMember));


        //orElse orElseGet 비교
        String checkName = "steve";
        String nonName=null;
        String result = Optional.ofNullable(checkName).orElse(defaultName());
        System.out.println("orElse" + result);

        result = Optional.ofNullable(checkName).orElseGet(OptionalTest::defaultName);
        System.out.println("orElseGet" + result);


        //optional을 사용해서 효율적으로 코드 쓰기

        String text = "test";
        int length;
        if (text != null) {
            length = text.length();
        } else {
            length = 0;
        }
        System.out.println("Optional 사용안함" + length);

        int length2 = Optional.ofNullable("test").map(String::length).orElseGet(() -> 0);

        System.out.println("Optional 사용함" + length2);


        //optional 쓰임새

        Map<Integer, String> cities = new HashMap<>();
        cities.put(1, "seoul");
        cities.put(2,"sydney");
        cities.put(3,"bangkok");

        Optional<String> maybeCity = Optional.ofNullable(cities.get(4));
        int length3 = maybeCity.map(String::length).orElse(0);
        System.out.println("optional사용" +  length3);

        List<String> cities2 = Arrays.asList("seoul","sydney","bangkok");
        Optional<String> maybeCity2 = getAsOptional(cities2, 3);
        length3 = maybeCity2.map(String::length).orElse(0);
        System.out.println("optional 사용 예외처리" + length3);

        maybeCity2.ifPresent(city -> {
            System.out.println("ifpresent 사용 "+ city.length());
        });

    }
    public static String defaultName(){
        System.out.println("return default name");
        return "default name";
    }
    public static <T> Optional<T> getAsOptional(List<T> list, int index){
        try{
            return Optional.of(list.get(index));
        }catch (ArrayIndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    //oprional을 활용하여 nullsafe한 메서드 만들기
    public String getCityOfMemberFromOrder(Order order){
        return Optional.ofNullable(order)
                .map(Order::getMember)
                .map(Member::getAddress)
                .map(Address::getCity)
                .orElse("서울");
    }

//   public Member getMemberIfOrderWithin(Order order, int min){
//        if (order !=null && order.getData().getTime() > System.currentTimeMillis() - min*1000){
//            return order.getMember();
//        }
//    }

    public Optional<Member> getMemberIfOrderWithin(Order order, int min){
        return Optional.ofNullable(order)
                .filter(o -> o.getData().getTime() > System.currentTimeMillis() - min*1000)
                .map(Order::getMember);
    }
}
