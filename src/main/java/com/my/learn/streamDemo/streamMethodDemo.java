package com.my.learn.streamDemo;

import com.my.learn.lambdaDemo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public void test02() {
        //filter
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        long count = arrayList.stream().count();
        System.out.println(count);
        System.out.println("--------------------------------");
        arrayList.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.length() > 2) {
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
    public void test03() {
        //limit
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        Stream<String> limit = arrayList.stream().limit(3);
        List<String> collect = limit.collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @Test
    public void test04() {
        //skip
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
//        arrayList.stream().skip(2).forEach(System.out::println);
        //大于集合长度返回空集合
        List<String> collect = arrayList.stream().skip(8).collect(Collectors.toList());
        System.out.println(collect.size());
    }

    @Test
    public void test05() {
        //map
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        List<Integer> collect = arrayList.stream().map((String name) -> {
            return name.length();
        }).collect(Collectors.toList());
        System.out.println(collect.toString());

    }

    @Test
    public void test06() {
        //sorted
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "宋源泉", "圣诞节", "集控室", "健康", "维恩");
        //sorted()默认排序
        arrayList.stream().sorted().forEach(System.out::println);

        //转出来的是Integer类型而不是int,因为int[]不行会变成int[]类型
        Stream<Integer> stream = Stream.of(22, 53, 24, 75, 22);
        stream.sorted().forEach(System.out::println);

        Stream.of(22, 53, 24, 75, 22).sorted((Integer a, Integer b) -> {
            return b - a;
        }).forEach(System.out::println);
    }

    @Test
    public void test07() {
        //distinct
        //转出来的是Integer类型而不是int,因为int[]不行会变成int[]类型
        Stream<Integer> stream = Stream.of(22, 53, 24, 75, 22);
        stream.distinct().forEach(System.out::println);
        //自定义对象的去重是根据equals和hashCode去重的
        Stream.of(new Person("John", 2), new Person("Key", 5)
                , new Person("Key", 5)).distinct().forEach(System.out::println);
    }

    @Test
    public void test08() {
        //match
        //stream流只能使用一次,如果要再使用要重新创建流,
        // 否则会java.lang.IllegalStateException: stream has already been operated upon or closed
        Stream<Integer> stream = Stream.of(22, 53, 24, 75, 22);
        boolean allMatch = stream.allMatch(e -> e > 55);
        System.out.println(allMatch);
        Stream<Integer> stream1 = Stream.of(22, 53, 24, 75, 22);
        boolean allMatch1 = stream1.anyMatch(e -> e > 55);
        System.out.println(allMatch1);
        Stream<Integer> stream2 = Stream.of(22, 53, 24, 75, 22);
        boolean allMatch2 = stream2.noneMatch(e -> e > 55);
        System.out.println(allMatch2);
    }

    @Test
    public void test09() {
        //find
        //findFirst是返回stream中第一个元素,findAny是返回stream中任意一个元素
        //在串行流中两者返回的是相同的,在并行流中findFirst返回处理结果中的第一个元素,findAny返回最先处理完的元素
        //findAny在并行流中限制少一些
        Stream<Integer> stream = Stream.of(22, 53, 24, 75, 22);
        Optional<Integer> first = stream.findFirst();
        System.out.println(first.get());

        Optional<Integer> any = Stream.of(22, 53, 24, 75, 22).findAny();
        System.out.println(any.get());

    }

    public static boolean getThree(String name) {
        if (name.length() > 2) {
            return true;
        }
        return false;
    }
}
