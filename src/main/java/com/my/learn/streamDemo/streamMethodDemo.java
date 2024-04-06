package com.my.learn.streamDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamMethodDemo {
    @Test
    public void testForEach() {
        //forEach
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        for (String s : arrayList) {
            System.out.println(s);
        }

        System.out.println("--------------------------------");

        arrayList.stream().forEach(e -> System.out.println(e));

        System.out.println("--------------------------------");

        arrayList.stream().forEach(System.out::println);

        System.out.println("--------------------------------");
        //stream.of转集合出来的还是集合
        Stream<ArrayList<String>> list = Stream.of(arrayList);

        Stream.of(arrayList).forEach(System.out::println);
    }

    @Test
    public void test02(){
        //filter
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        long count = arrayList.stream().count();
        System.out.println(count);
        System.out.println("--------------------------------");
        arrayList.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.length() > 2){
                    return true;
                }
                return false;
            }
        }).forEach(System.out::println);
        System.out.println("--------------------------------");
        arrayList.stream().filter(e -> e.length() > 2).forEach(System.out::println);
        System.out.println("--------------------------------");
        arrayList.stream().filter(streamMethodDemo::getThree).forEach(System.out::println);
    }

    @Test
    public void test03(){
        //limit
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        Stream<String> limit = arrayList.stream().limit(3);
        List<String> collect = limit.collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @Test
    public void test04(){
        //skip
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
//        arrayList.stream().skip(2).forEach(System.out::println);
        //大于集合长度返回空集合
        List<String> collect = arrayList.stream().skip(8).collect(Collectors.toList());
        System.out.println(collect.size());
    }

    @Test
    public void test05(){
        //skip
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        List<Integer> collect = arrayList.stream().map((String name) -> {
            return name.length();
        }).collect(Collectors.toList());
        System.out.println(collect.toString());

    }

    public static boolean getThree(String name){
        if (name.length() > 2){
            return true;
        }
        return false;
    }
}
